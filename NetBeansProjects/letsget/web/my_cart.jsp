<%-- 
    Document   : hmy_cart
    Created on : Nov 24, 2017
    Author     : Andrew
--%>
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
        <title>My Cart</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
        <!--[if lte IE 8]>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-old-ie-min.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
        <!--<![endif]-->
        <link rel="stylesheet" href="styles/my_cartstyle.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style src="hompagestyle.css"></style>
    </head>
    <body>
        <%
            if (session.getAttribute("type") != null) {
                if (session.getAttribute("type").equals("a")) {
                    response.sendRedirect("adminInventory.jsp");
                } else if (session.getAttribute("type").equals("e")) {
                    response.sendRedirect("shipmentEmployee.jsp");
                } else if (session.getAttribute("type").equals("m")) {
                    response.sendRedirect("managerReports.jsp");
                }
            }
            //allow access only if session exists
            String userID = null;
            if (session.getAttribute("userID") == null) {
                response.sendRedirect("login_register.jsp");
            } else {
                userID = (String) session.getAttribute("userID");
            }
            String userName = null;
            String sessionID = null;
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
                }
            } else {
                sessionID = session.getId();
            }

            CartDAO cartDB = new CartDAO();
            CartObject[] cartContents = cartDB.getCartContents(userID);
            //generatre isbn list for sql query
            String isbns = "";
            for (int i = 0; i < cartContents.length; i++) {
                isbns += cartContents[i].isbn + ",";
            }
            Double totalPrice = 0.0;

        %>

        <!-- Menu toggle -->
        <a href="#menu" id="menuLink" class="menu-link">
            <!-- Hamburger icon -->
            <span></span>
        </a>
        <div class="pure-g">
            <div class="pure-u-1-4"> 
                <div id ="menu" class="pure-menu">
                    <a class="pure-menu-heading" href="#">Welcome<%="\n " + userName%></a>
                    <ul class="pure-menu-list">
                        <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="homepage.jsp" class="pure-menu-link">Home</a></li>
                        <li class="pure-menu-item"><a href="#" class="pure-menu-link">My Cart</a></li>
                        <li class="pure-menu-item"><a href="search.jsp" class="pure-menu-link">Search</a></li>
                        <li class="pure-menu-item"><a href="my_accountName.jsp" class="pure-menu-link">My Account</a></li>
                        <li class="pure-menu-item"><a href="login_register.jsp" class="pure-menu-link">Log In/Register</a></li>
                        <li class="pure-menu-item"><a href="signout" class="pure-menu-link">Sign Out</a></li>
                    </ul>
                </div>
            </div>
            <div class="pure-u-5-8">
                <div class="banner">
                    <h1 class="bannerhead">
                        <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
                    </h1>
                </div>

                <div class="browseheader">
                    BOOKS IN CART
                </div>

                <!--THE TABLE:::-->
                <%
                    //String driverName = "com.mysql.jdbc.Driver";
                    //String connectionUrl = "letsgetthrifty-database.cgg5mwmrnbyi.us-east-1.rds.amazonaws.com:3306/";
                    //String dbName = "letsget";
                    //String userId = "admin";
                    //String password = "password";
                    String driverName = "com.mysql.jdbc.Driver";
                    String connectionUrl = "jdbc:mysql://letsgetthrifty-database.cgg5mwmrnbyi.us-east-1.rds.amazonaws.com:3306/letsget?zeroDateTimeBehavior=convertToNull";
                    String dbName = "letsget";
                    String userId = "admin";
                    String password = "password";

                    try {
                        Class.forName(driverName);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    Connection connection = null;
                    Statement statement = null;
                    ResultSet resultSet = null;
                %>

                <table>
                    <tr>

                    </tr>

                    <%
                        if (isbns.length() > 0) {
                            isbns = isbns.substring(0, isbns.length() - 1);

                            try {
                                connection = DriverManager.getConnection(connectionUrl, userId, password);
                                statement = connection.createStatement();
                                String sql = "SELECT * FROM books WHERE isbn IN (" + isbns + ")";
                                resultSet = statement.executeQuery(sql);
                                for (int i = 0; i < cartContents.length; i++) {
                                    resultSet.next();
                                    String isbn = resultSet.getString("isbn");
                                    int quantity = 0;
                                    for (int j = 0; j < cartContents.length; j++) {
                                        if (isbn.equals(cartContents[j].isbn)) {
                                            quantity = cartContents[j].quantity;
                                        }
                                    }
                                    double priceSingle = quantity * Double.parseDouble(resultSet.getString("sellPrice"));
                                    totalPrice += priceSingle;
                                    totalPrice = Math.floor(totalPrice * 100) / 100;
                    %>
                    <tr>  
                        <%
                            String primaryKey = resultSet.getString("isbn");
                            String pic = resultSet.getString("picture");
                        %>
                        <td><image src ="<%=pic%>" height="200" width=130"></td>    
                        <td>
                            <h3><%=resultSet.getString("title")%></h3>
                            <b><%=resultSet.getString("author")%></b><br><br>
                            <%=resultSet.getString("genre")%><br>
                            <%=resultSet.getString("rating")%>/5
                            <div class="spacer"><h1></h1></div></td>
                        <td><h4>$<%=resultSet.getString("sellPrice")%></h4></td>
                        <td>
                            <br>
                            <form class="pure-form" method = "post" action = "cartmanager">
                                <input type="hidden" name="isbn" value=<%=primaryKey%>>
                                <input type="hidden" name="price" value=<%=resultSet.getString("sellPrice")%>>
                                <input type="hidden" name="update" value="true">
                                <input type="text" name="quantity" value=<%=quantity%>>
                                <button type="submit" class="pure-button">UPDATE</button>
                            </form>
                            <form class="pure-form" method = "post" action = "remove">
                                <input type="hidden" name="isbn" value=<%=primaryKey%>>
                                <input type="hidden" name="quantity" value=<%=cartContents[i].quantity%>>
                                <button type="submit" class="pure-button remove-button">REMOVE</button>
                            </form>
                    </tr>

                    <%
                                }
                                connection.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    %>
                </table>       
                <!--END OF TABLE-->
                <div class="spacertwo"></div>
                <div class="pure-g">
                    <div class="pure-u-1-3"> 
                    </div>
                    <div class="pure-u-1-3"> 
                    </div>
                    <div class="pure-u-1-3"> 
                        <div class="summaryheader">
                            SUMMARY
                        </div>
                        <div class="promo">
                            <%
                                DecimalFormat df = new DecimalFormat("#.00");
                                String subtotal = df.format(totalPrice);
                                String shipping = df.format(totalPrice / 10);
                                String total = df.format(totalPrice + totalPrice / 10);
                            %>
                            <div class="summary">
                                <table>
                                    <tr>
                                        <td><b>Subtotal:</b></td> 
                                        <td><%=totalPrice%></td>
                                    </tr>
                                    <tr>
                                        <td><b>Shipping:</b></td>
                                        <td><%=shipping%></td>
                                    </tr>
                                    <tr>
                                        <td><h3>Total Price:</h3></td>
                                        <td><h3><%=total%></h3></td>
                                    </tr>
                                </table>
                            </div>
                            <form method = "post" action = "promocheck">
                                <input type="text" name="promo" placeholder="Enter Promo Code Here">
                                <button type="submit" class="pure-button">PROCEED TO CHECKOUT</button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <div class="pure-u-1-8"> 
            </div>
        </div>
        <script src="scripts/homepage.js"></script>
    </body>
</html>
