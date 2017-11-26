/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.CartDAO;
import entity.CartObject;
import entity.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
        String errorMsg = "";
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

        System.out.println(useShip + " " + usePay + " " + useBill);
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
        while(!found){
            orderID = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
            OrderDAO db = new OrderDAO();
            found = db.checkIDValidity(orderID);
        }
        System.out.println("OrderID"+orderID);
        //what I need to place an order is here:
        //orderID, shippintAgencyID, orderStatus, orderDate, shippingAddress, billingAddress
        //paymentMethod, confirmationNumber, userID, orderTotal, creditCardID

        //first thing is to generate an order id
        //in order to add the book to the transaction:
        //1.) check to make sure it is in stock, if not leave in cart and add to not in stock error
        //2.) if it is in stock, remove it from stock and from the cart
        //3.) add a transaction record for the book and tag the orderID
        //transaction record has several fields
        //transactionID, orderID, isbn, qty, promoID, total
        //when all books are created as transactions
        //1.) create an order database entry
        //2. add shipping agency (default to ups)
        //3. order status will be set to ordered
        //4. order date will be the current date
        //5. shipping address if useShip is true, we will use the default user shipping address
        //5a otherwise we will create a new shipping address and use that
        //6. for now the type will be card
        //7. if useBill is true we will use the default shipping address
        //7a otherwise we will create a new address and use that
        //8. we will create a confirmation number like in registration
        //9. userID will be the current user
        //10 order total will be the sum of all of the order linked transactions, after the promo code has been applied
        //11 creditcardID if usePay is true, we will use the default user payment
    }
}
