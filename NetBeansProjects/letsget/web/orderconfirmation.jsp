<%-- 
    Document   : orderconfirmation
    Created on : Nov 29, 2017, 2:00:16 PM
    Author     : andrewjacobsen
--%>

<%@page import="entity.User"%>
<%@page import="entity.Payment"%>
<%@page import="entity.Address"%>
<%@page import="entity.ShippingAgency"%>
<%@page import="entity.Order"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.logging.Level"%>
<%@page import="services.DatabaseUtility"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="entity.CartObject"%>
<%@page import="entity.CartDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Order Confirmation</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
        <!--[if lte IE 8]>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-old-ie-min.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
        <!--<![endif]-->
        <link rel="stylesheet" href="styles/orderconfirmationstyle.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">

    </head>
    <body>
        <%
            //allow access only if session exists
            String userID = null;
            if (session.getAttribute("userID") == null) {
                response.sendRedirect("login_register.jsp");
            } else {
                userID = (String) session.getAttribute("userID");
            }
            String userName = null;
            String sessionID = null;
            String orderID = null;
            String error = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userID")) {
                        userID = cookie.getValue();
                    }
                    if (cookie.getName().equals("userName")) {
                        userName = cookie.getValue();
                    }
                    if (cookie.getName().equals("JSESSIONID")) {
                        sessionID = cookie.getValue();
                    }
                    if (cookie.getName().equals("orderID")) {
                        orderID = cookie.getValue();
                    }
                    if (cookie.getName().equals("error")) {
                        error = cookie.getValue();
                        error = error.replaceAll("_", " ");
                        error = error.replaceAll("%", "\n");
                        cookie.setMaxAge(0);
                    }
                }
            } else {
                sessionID = session.getId();
            }

        %>

        <div class="pure-g">
            <div class="pure-u-1-1"> 

                <div class="banner">
                    <h1 class="bannerhead">
                        <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
                    </h1>
                </div>
                <div class="pure-g">
                    <div class="pure-u-1-4"></div>
                    <div class="pure-u-1-2">
                        <div class="orderheader">
                            ORDER CONFIRMATION
                        </div>
                        <div class= "ordersummary">

                            <h2>Below are your order details, they have also been emailed to you.</h2>

                            <%// this where we will get all the order details.
                                Order order = new Order(orderID);
                                ShippingAgency agency = new ShippingAgency("" + order.getShippingAgencyID());
                                Address ship = new Address(order.getShippingAddress());
                                Address bill = new Address(order.getBillingAddress());
                                Payment pay = new Payment(order.getCreditCardID(), true);
                                User user = new User(userID, "u");
                            %>
                            <div class="orderheader">
                                Summary
                            </div>

                            <table>
                                <tr>
                                    <th class="lhs">Order ID<br></th>
                                    <th class="rhs"><%=order.getOrderID()%></th>
                                </tr>
                                <tr>
                                    <th class="lhs">Name<br></th>
                                    <th class="rhs"><%=user.getFN() + " " + user.getLN()%></th>
                                </tr>
                                <tr>
                                    <td class="lhs">User ID<br></td>
                                    <td class="rhs"><%=order.getUserID()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">Shipping Agency<br></td>
                                    <td class="rhs"><%=agency.getAgencyName()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">Payment Method<br></td>
                                    <td class="rhs"><%=order.getPaymentMethod()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">Confirmation Number<br></td>
                                    <td class="rhs"><%=order.getConfirmationNumber()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">Order Status<br></td>
                                    <td class="rhs"><%=order.getOrderStatus()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">Order Date<br></td>
                                    <td class="rhs"><%=order.getOrderDate()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">Order Total<br></td>
                                    <td class="rhs">$<%=order.getOrderTotal()%></td>
                                </tr>
                            </table>
                            <div class="orderheader">
                                Shipping
                            </div>
                            <table>
                                <tr>
                                    <td class="lhs">Street<br></td>
                                    <td class="rhs"><%=ship.getStreet()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">City<br></td>
                                    <td class="rhs"><%=ship.getCity()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">State<br></td>
                                    <td class="rhs"><%=ship.getState()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">Zipcode<br></td>
                                    <td class="rhs"><%=ship.getZip()%></td>
                                </tr>
                            </table>
                            <div class="orderheader">
                                Payment
                            </div>
                            <table>
                                <tr>
                                    <td class="lhs">Card<br></td>
                                    <td class="rhs"><%=pay.getNum()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">Expiration<br></td>
                                    <td class="rhs"><%=pay.getExp()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">Type<br></td>
                                    <td class="rhs"><%=pay.getType()%></td>
                                </tr>
                            </table>
                            <div class="orderheader">
                                Billing
                            </div>
                            <table>
                                <tr>
                                    <td class="lhs">Street<br></td>
                                    <td class="rhs"><%=bill.getStreet()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">City<br></td>
                                    <td class="rhs"><%=bill.getCity()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">State<br></td>
                                    <td class="rhs"><%=bill.getState()%></td>
                                </tr>
                                <tr>
                                    <td class="lhs">Zipcode<br></td>
                                    <td class="rhs"><%=bill.getZip()%></td>
                                </tr>
                            </table>

                            <div id="error">
                                <h3>Our apologies, these books in your order were out of stock:<br>
                                    <%=error%><br>They remain in your cart for a purchase in the future!</h3>                                
                            </div>
                            <div class="pure-g">
                                <div class="pure-u-1-3"></div>
                                <div class="pure-u-1-3">
                                    <br>
                                    <form action="homepage.jsp">
                                        <button type="submit" class="pure-button login pure-input-1 pure-button-primary">Return to home</button>
                                    </form>
                                </div>
                                <div class="pure-u-1-3"></div>
                            </div>

                        </div>
                        <div class="pure-u-1-4"></div>
                    </div>
                </div>
            </div>

    </body>
</html>

