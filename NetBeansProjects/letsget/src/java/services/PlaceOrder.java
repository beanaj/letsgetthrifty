/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Book;
import entity.BookDAO;
import entity.CartDAO;
import entity.CartObject;
import entity.Order;
import entity.OrderDAO;
import entity.Transaction;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andrewjacobsen
 */
public class PlaceOrder extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        //creat error string for out of stock inventory
        String errorMsg = "Our apologies, some of the books in your order are out of stock. The books will remain in your cart, please try again in the future.";
        errorMsg += "\nThe below books are out of stock: \n";
        Boolean outOfStock = false;
        //user id for order being placed
        String userID = (String) session.getAttribute("userID");

        boolean useShip = false;
        boolean usePay = false;
        boolean useBill = false;

        String value = request.getParameter("ship");
        if (value != null) {
            useShip = true;
        }
        value = request.getParameter("paybox");
        if (value != null) {
            usePay = true;
        }
        value = request.getParameter("bill");
        if (value != null) {
            useBill = true;
        }
        //shipping information
        String shippingStreet = request.getParameter("street");
        String shippingCity = request.getParameter("city");
        String shippingState = request.getParameter("state");
        String shippingZip = request.getParameter("zip");
        //billing information
        String billingStreet = request.getParameter("bstreet");
        String billingCity = request.getParameter("bcity");
        String billingState = request.getParameter("bstate");
        String billingZip = request.getParameter("bzip");

        CartDAO cartDB = new CartDAO();
        Double discount = cartDB.getCartPromo(userID);
        String promoCode = "";
        if (discount > 0) {
            promoCode = cartDB.getCartPromoCode(userID);
        }

        CartObject[] cartContents = cartDB.getCartContents(userID);
        //generatre isbn list of cart
        String isbns = "";
        for (int i = 0; i < cartContents.length; i++) {
            isbns += cartContents[i].isbn + ",";
        }

        //generate an orderID
        boolean found = false;
        int orderID = 0;
        //this is generating the id while also ensuring that the id is not already in use
        do {
            orderID = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
            OrderDAO db = new OrderDAO();
            found = db.checkIDValidity(orderID);
        } while (found);

        //what I need to place an order is here:
        //orderID, shippintAgencyID, orderStatus, orderDate, shippingAddress, billingAddress
        //paymentMethod, confirmationNumber, userID, orderTotal, creditCardID
        //in order to add the book to the transaction:
        //1.) check to make sure it is in stock, if not leave in cart and add to not in stock error
        boolean[] inStock = new boolean[cartContents.length];
        for (int i = 0; i < cartContents.length; i++) {
            Book book = new Book(cartContents[i].isbn);
            int quantityInStock = book.getQtyInStock();
            int quantityInOrder = cartContents[i].quantity;
            if (quantityInStock < quantityInOrder) {
                inStock[i] = false;
                outOfStock = true;
                errorMsg += "" + book.getTitle();
            } else {
                inStock[i] = true;
            }
        }
        //2.) if it is in stock, remove it from stock and from the cart
        //remove the books from stock
        for (int i = 0; i < cartContents.length; i++) {
            if (inStock[i] == true) {
                Book book = new Book(cartContents[i].isbn);
                int quantityInStock = book.getQtyInStock();
                int quantityInOrder = cartContents[i].quantity;
                int newTotal = quantityInStock - quantityInOrder;
                book.setQtyInStock(newTotal);
                BookDAO db = new BookDAO();
                db.updateBook(book.getISBN(), book.getGenre(),
                        book.getAuthor(), book.getTitle(), book.getRating(), book.getPicture(),
                        book.getEdition(), book.getPublisher(), book.getPublicationYear(),
                        book.getQtyInStock(), book.getMinThreshold(), book.getBuyPrice(), book.getSellPrice(), book.getSupplierID());

            }
        }
        //remove the books from the cart
        for (int i = 0; i < cartContents.length; i++) {
            if (inStock[i] == true) {
                Book book = new Book(cartContents[i].isbn);
                CartDAO db = new CartDAO();
                db.removeFromCart(book.getISBN(), userID);
            }
        }

        //get current time
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        Order order = new Order(orderID, 1, "oStat", timeStamp, "sAdd", "bAdd", "pay", "conf", userID, "orderT", "cCard");
        order.addOrder();
        //transaction record has several fields
        //orderID, isbn, qty, promoID, total
        //3.) add a transaction record for the book and tag the orderID
        for (int i = 0; i < cartContents.length; i++) {
            if (inStock[i] == true) {
                Book book = new Book(cartContents[i].isbn);
                CartDAO db = new CartDAO();
                db.removeFromCart(book.getISBN(), userID);
                String[] info = new String[5];
                info[0] = "" + orderID;
                info[1] = book.getISBN();
                info[2] = "" + cartContents[i].quantity;
                info[3] = "" + db.getCartPromo(userID);
                Double price = 0.0;
                if (discount > 0) {
                    price = (book.getSellPrice() * cartContents[i].quantity) * discount;
                } else {
                    price = (book.getSellPrice() * cartContents[i].quantity);
                }
                price = Math.floor(price * 100) / 100;
                info[4] = "" + price;
                Transaction tr = new Transaction(info);
                tr.addToDatabase();
            }
        }
        //when all books are created as transactions
        //1.) create an order database entry **DONE ABOVE**
        order.setOrderID(orderID);
        System.out.println(order.getOrderID());
        //2. add shipping agency (default to ups)
        order.setShippingAgencyID(1);
        //3. order status will be set to ordered
        order.setOrderStatus("Placed");
        //4. order date will be the current date
        order.setOrderDate(timeStamp);
        //5. shipping address if useShip is true, we will use the default user shipping address
        if (useShip) {
            //shipping address default is
            String addressID = "a" + userID.replaceAll("[^\\d]", "");
            order.setShippingAddress(addressID);
        } else {
            //5a otherwise we will create a new shipping address and use that

        }
        //6. for now the payment method will be card
        order.setPaymentMethod("card");
        //7. if useBill is true we will use the default shipping address
        if(useBill){
            String addressID = "a" + userID.replaceAll("[^\\d]", "");
            order.setBillingAddress(addressID);
        }else{
                    //7a otherwise we will create a new address and use that
        }

        //8. we will create a confirmation number like in registration
        String code = UUID.randomUUID().toString();
        order.setConfirmationNumber(code);
        //9. userID will be the current user
        order.setUserID(userID);
        //10 order total will be the sum of all of the order linked transactions, after the promo code has been applied
        order.setOrderTotal("100.0");
        //11 creditcardID if usePay is true, we will use the default user payment
        if (usePay) {
            String paymentID = "999" + userID.replaceAll("[^\\d]", "");
            order.setCreditCardID(paymentID);
        } else {
            
        }
        OrderDAO db = new OrderDAO();
        db.updateOrder(order, order.getOrderID());
    }
}
