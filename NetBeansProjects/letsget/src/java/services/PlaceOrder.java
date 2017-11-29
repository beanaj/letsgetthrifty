/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Address;
import entity.Book;
import entity.BookDAO;
import entity.CartDAO;
import entity.CartObject;
import entity.Order;
import entity.OrderDAO;
import entity.Payment;
import entity.Transaction;
import entity.TransactionDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletContext;
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

    private String host;
    private String port;
    private String user;
    private String pass;

    public void init() {
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

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
        Double discount = cartDB.getCartPromoDiscount(userID);
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
                try {
                    db.updateBook(book.getISBN(), book.getGenre(),
                            book.getAuthor(), book.getTitle(), book.getRating(), book.getPicture(),
                            book.getEdition(), book.getPublisher(), book.getPublicationYear(),
                            book.getQtyInStock(), book.getMinThreshold(), book.getBuyPrice(), book.getSellPrice(), book.getSupplierID());
                } catch (SQLException ex) {
                    String error = "Error: Invalid input";
                    //DO SOMETHING WITH ERROR
//                    request.setAttribute("error", error);
//                    request.getRequestDispatcher("adminPromotions.jsp").forward(request, response);
                }

            }
        }
        //remove the books from the cart
        //fetch the promoid first 
        CartDAO db = new CartDAO();
        String promoID = db.getCartPromoCode(userID);
        for (int i = 0; i < cartContents.length; i++) {
            if (inStock[i] == true) {
                Book book = new Book(cartContents[i].isbn);
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
                db.removeFromCart(book.getISBN(), userID);
                String[] info = new String[5];
                info[0] = "" + orderID;
                info[1] = book.getISBN();
                info[2] = "" + cartContents[i].quantity;
                info[3] = promoID;
                Double price = 0.0;
                if (discount > 0) {
                    price = (book.getSellPrice() * cartContents[i].quantity) * discount / 100;
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
            Address newAddress = new Address();
            newAddress.addressID = "" + orderID;
            newAddress.street = request.getParameter("street");
            newAddress.city = request.getParameter("city");
            newAddress.state = request.getParameter("state");
            newAddress.zip = request.getParameter("zip");
            newAddress.userID = userID;
            newAddress.submit("o");
            order.setShippingAddress(newAddress.addressID);
        }
        //6. for now the payment method will be card
        order.setPaymentMethod("card");
        //7. if useBill is true we will use the default shipping address
        if (useBill) {
            String addressID = "a" + userID.replaceAll("[^\\d]", "");
            order.setBillingAddress(addressID);
        } else {
            //7a otherwise we will create a new address and use that
            Address newAddress = new Address();
            newAddress.addressID = "" + orderID + "-B";
            newAddress.street = request.getParameter("bstreet");
            newAddress.city = request.getParameter("bcity");
            newAddress.state = request.getParameter("bstate");
            newAddress.zip = request.getParameter("bzip");
            newAddress.userID = userID;
            newAddress.submit("o");
            order.setBillingAddress(newAddress.addressID);
        }

        //8. we will create a confirmation number like in registration
        String code = UUID.randomUUID().toString();
        order.setConfirmationNumber(code);
        //9. userID will be the current user
        order.setUserID(userID);
        //10 order total will be the sum of all of the order linked transactions, after the promo code has been applied
        TransactionDAO dbT = new TransactionDAO();
        double total = dbT.getPrice("" + orderID);
        total = total + total * .1;
        total = Math.floor(total * 100) / 100;
        order.setOrderTotal("" + total);
        //11 creditcardID if usePay is true, we will use the default user payment
        if (usePay) {
            String paymentID = "999" + userID.replaceAll("[^\\d]", "");
            order.setCreditCardID(paymentID);
        } else {
            Payment card = new Payment();
            card.setCCID("" + orderID);
            card.setExp(request.getParameter("exp"));
            card.setNum(request.getParameter("card"));
            card.setType(request.getParameter("type"));
            card.setUserID(userID);
            card.submit();
            order.setCreditCardID(card.getCCID());
        }
        OrderDAO dbO = new OrderDAO();
        dbO.updateOrder(order, order.getOrderID());

        //now i need to send an email with a summary of the order
        User userObj = new User(userID, "u");
        String name = userObj.getFN() + " " + userObj.getLN();
        String subject = "Let's Get Thrifty - Your Order has been Placed!";
        String message = createOrderEmail(name, "" + orderID, "" + order.getShippingAgencyID(), order.getOrderDate(),
                order.getOrderTotal(), order.getConfirmationNumber());

        try {
            String resultMessage = "";
            //send email to registered user
            try {
                EmailSender.sendEmail(host, port, user, pass, userObj.getEmail(), subject, message);
                resultMessage = "The e-mail was sent successfully";
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = "There were an error: " + ex.getMessage();
            }
        } finally {
            //this will need to be changed to a non absolute address
            Cookie orderIDCookie = new Cookie("orderID", ""+orderID);
            response.addCookie(orderIDCookie);
            //Get the encoded URL string
            String encodedURL = response.encodeRedirectURL("orderconfirmation.jsp");
            response.sendRedirect(encodedURL);

        }
    }

    private String createOrderEmail(String name, String orderID, String shippingAgency, String orderDate, String orderTotal,
            String confirmationCode) {
        //generate an HTML response email.
        String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + " <head>\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                + "  <title>Order Details</title>\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n"
                + "</head>\n"
                + "    \n"
                + "<body style=\"margin: 0; padding: 0;\">\n"
                + " <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "  <tr>\n"
                + "   <td>\n"
                + "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse;\">\n"
                + "    \n"
                + "    <tr>\n"
                + "      <td>\n"
                + "          <b>Your order has been successfully placed. <br>Here is your confirmation, check MyAccount for more details. <br></b><br><br>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "    <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Name:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + name
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "    <tr>\n"
                + "      <td>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Order ID:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + orderID
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           ShippingAgency:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + shippingAgency
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Order Time:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + orderDate
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Total:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "$" + orderTotal
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "       <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Confirmation Code:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + confirmationCode
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           \n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                //               + exp
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           \n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                //             + type
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           \n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                //               + accountID
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "        <tr>\n"
                + "      <td>\n"
                + "       \n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "        <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\"><br>\n"
                + "           \n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\"><br>\n"
                //                + code
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "    </table>\n"
                + "   </td>\n"
                + "  </tr>\n"
                + " </table>\n"
                + "</body>\n"
                + "</html>";
        return msg;
    }
}
