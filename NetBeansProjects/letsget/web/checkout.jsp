<%-- 
    Document   : checkout
    Created on : Nov 24, 2017
    Author     : Andrew
--%>
<%@page import="entity.User"%>
<%@page import="entity.Payment"%>
<%@page import="entity.Address"%>
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
        <title>Checkout</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
        <!--[if lte IE 8]>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-old-ie-min.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
        <!--<![endif]-->
        <link rel="stylesheet" href="styles/checkoutstyle.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="scripts/checkoutscript.js"></script>
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
            Double discount = cartDB.getCartPromoDiscount(userID);
            String discountFormat = "" + discount;
            discount = discount / 100;
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
                        <li class="pure-menu-item"><a href="my_cart.jsp" class="pure-menu-link">My Cart</a></li>
                        <li class="pure-menu-item"><a href="search.jsp" class="pure-menu-link">Search</a></li>
                        <li class="pure-menu-item"><a href="my_account.jsp" class="pure-menu-link">My Account</a></li>
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

                <div class="pure-g">
                    <form class="pure-form" method = "post" action = "placeorder">
                        <div class="pure-u-3-4">
                            <div class="browseheader">
                                ORDER CONTENTS
                            </div>
                            <%
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
                                    <td>
                                        <%=resultSet.getString("title")%>
                                        <div class="spacer"><h1></h1></div></td>
                                    <td>Qty: <%=quantity%></td>
                                    <td>$<%=resultSet.getString("sellPrice")%></td>

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
                            <%
                                //here is where I will gather all user information
                                String addressID= "a"+userID.substring(2);
                                Address userAddress = new Address(addressID, userID);
                                //gather the users default payment
                                Payment userPayment = new Payment(userID);
                                User user = new User(userID, "u");
                            %>

                            <div class="browseheader">
                                SHIPPING ADDRESS  
                            </div>
                            <label for="option-one" class="pure-checkbox">
                                    <input name="ship" id="defaultShipping" type="checkbox" value="" onchange="useDefault(this,'<%=userAddress.street%>','<%=userAddress.city%>','<%=userAddress.state%>','<%=userAddress.zip%>')">
                                    Use my default shipping address
                            </label>
                            <form class="pure-form" method = "post" action = "registered">
                                <fieldset class="pure-group">
                                    <input type="text" class="pure-input-1" placeholder="Street" name = "street" id="street" required>
                                    <input type="text" class="pure-input-1" placeholder="City" name = "city" id="city" required>
                                    <select class="pure-input-1" name= "state" id="state" required>
                                        <option disabled selected value>Select State</option>
                                        <option value="AL">Alabama</option>
                                        <option value="AK">Alaska</option>
                                        <option value="AZ">Arizona</option>
                                        <option value="AR">Arkansas</option>
                                        <option value="CA">California</option>
                                        <option value="CO">Colorado</option>
                                        <option value="CT">Connecticut</option>
                                        <option value="DE">Delaware</option>
                                        <option value="DC">District Of Columbia</option>
                                        <option value="FL">Florida</option>
                                        <option value="GA">Georgia</option>
                                        <option value="HI">Hawaii</option>
                                        <option value="ID">Idaho</option>
                                        <option value="IL">Illinois</option>
                                        <option value="IN">Indiana</option>
                                        <option value="IA">Iowa</option>
                                        <option value="KS">Kansas</option>
                                        <option value="KY">Kentucky</option>
                                        <option value="LA">Louisiana</option>
                                        <option value="ME">Maine</option>
                                        <option value="MD">Maryland</option>
                                        <option value="MA">Massachusetts</option>
                                        <option value="MI">Michigan</option>
                                        <option value="MN">Minnesota</option>
                                        <option value="MS">Mississippi</option>
                                        <option value="MO">Missouri</option>
                                        <option value="MT">Montana</option>
                                        <option value="NE">Nebraska</option>
                                        <option value="NV">Nevada</option>
                                        <option value="NH">New Hampshire</option>
                                        <option value="NJ">New Jersey</option>
                                        <option value="NM">New Mexico</option>
                                        <option value="NY">New York</option>
                                        <option value="NC">North Carolina</option>
                                        <option value="ND">North Dakota</option>
                                        <option value="OH">Ohio</option>
                                        <option value="OK">Oklahoma</option>
                                        <option value="OR">Oregon</option>
                                        <option value="PA">Pennsylvania</option>
                                        <option value="RI">Rhode Island</option>
                                        <option value="SC">South Carolina</option>
                                        <option value="SD">South Dakota</option>
                                        <option value="TN">Tennessee</option>
                                        <option value="TX">Texas</option>
                                        <option value="UT">Utah</option>
                                        <option value="VT">Vermont</option>
                                        <option value="VA">Virginia</option>
                                        <option value="WA">Washington</option>
                                        <option value="WV">West Virginia</option>
                                        <option value="WI">Wisconsin</option>
                                        <option value="WY">Wyoming</option>
                                    </select>
                                    <input id = "zip" type="text" class="pure-input-1" placeholder="Zipcode" name = "zip" onchange="checkZip(this)" required>

                                </fieldset>
                                

                                <div class="browseheader">
                                    PAYMENT
                                </div>
                                <label for="option-one" class="pure-checkbox">
                                    <input name="paybox" id="defaultShipping" type="checkbox" value="" onchange="useDefaultPay(this, '<%=userPayment.getNum()%>','<%=userPayment.getExp()%>','<%=userPayment.getType()%>','<%=user.getFN()%>','<%=user.getLN()%>')">
                                    Use my saved payment
                            </label>
                                <fieldset class="pure-group">
                                    <input id="firstname" type="text" class="pure-input-1" placeholder="First Name" name = "firstname" onchange="checkFN(this)" required>
                                    <input id="lastname" type="text" class="pure-input-1" placeholder="Last Name" name = "lastname" onchange="checkLN(this)" required>
                                    <input id="card" type="text" class="pure-input-1" placeholder="Card Number" name = "card" onchange="checkCard(this)" required>
                                    <input id = "exp" type="text" class="pure-input-1" placeholder="Expiration" name = "exp" onchange="checkExp(this)" required>
                                    <select id="type" class="pure-input-1" name = "type" required>
                                        <option disabled selected value>Select Card Type</option>
                                        <option>Visa</option>
                                        <option>MasterCard</option>
                                        <option>American Express</option>
                                        <option>Discover</option>
                                        <option>Capital One</option>
                                    </select>
                                </fieldset>
                                <div class="browseheader">
                                    BILLING ADDRESS
                                </div>
                                <label for="option-one" class="pure-checkbox">
                                    <input name="bill" id="defaultBilling" type="checkbox" value="" onclick="useDefaultBill(this,'<%=userAddress.street%>','<%=userAddress.city%>','<%=userAddress.state%>','<%=userAddress.zip%>')">
                                    Use my default shipping address
                                </label>
                                <fieldset class="pure-group">
                                    <input type="text" class="pure-input-1" placeholder="Street" name = "bstreet" id="bstreet" required>
                                    <input type="text" class="pure-input-1" placeholder="City" name = "bcity" id="bcity" required>
                                    <select class="pure-input-1" name= "bstate" id="bstate" required>
                                        <option disabled selected value>Select State</option>
                                        <option value="AL">Alabama</option>
                                        <option value="AK">Alaska</option>
                                        <option value="AZ">Arizona</option>
                                        <option value="AR">Arkansas</option>
                                        <option value="CA">California</option>
                                        <option value="CO">Colorado</option>
                                        <option value="CT">Connecticut</option>
                                        <option value="DE">Delaware</option>
                                        <option value="DC">District Of Columbia</option>
                                        <option value="FL">Florida</option>
                                        <option value="GA">Georgia</option>
                                        <option value="HI">Hawaii</option>
                                        <option value="ID">Idaho</option>
                                        <option value="IL">Illinois</option>
                                        <option value="IN">Indiana</option>
                                        <option value="IA">Iowa</option>
                                        <option value="KS">Kansas</option>
                                        <option value="KY">Kentucky</option>
                                        <option value="LA">Louisiana</option>
                                        <option value="ME">Maine</option>
                                        <option value="MD">Maryland</option>
                                        <option value="MA">Massachusetts</option>
                                        <option value="MI">Michigan</option>
                                        <option value="MN">Minnesota</option>
                                        <option value="MS">Mississippi</option>
                                        <option value="MO">Missouri</option>
                                        <option value="MT">Montana</option>
                                        <option value="NE">Nebraska</option>
                                        <option value="NV">Nevada</option>
                                        <option value="NH">New Hampshire</option>
                                        <option value="NJ">New Jersey</option>
                                        <option value="NM">New Mexico</option>
                                        <option value="NY">New York</option>
                                        <option value="NC">North Carolina</option>
                                        <option value="ND">North Dakota</option>
                                        <option value="OH">Ohio</option>
                                        <option value="OK">Oklahoma</option>
                                        <option value="OR">Oregon</option>
                                        <option value="PA">Pennsylvania</option>
                                        <option value="RI">Rhode Island</option>
                                        <option value="SC">South Carolina</option>
                                        <option value="SD">South Dakota</option>
                                        <option value="TN">Tennessee</option>
                                        <option value="TX">Texas</option>
                                        <option value="UT">Utah</option>
                                        <option value="VT">Vermont</option>
                                        <option value="VA">Virginia</option>
                                        <option value="WA">Washington</option>
                                        <option value="WV">West Virginia</option>
                                        <option value="WI">Wisconsin</option>
                                        <option value="WY">Wyoming</option>
                                    </select>
                                    <input id = "bzip" type="text" class="pure-input-1" placeholder="Zipcode" name = "bzip" onchange="checkZip(this)" required>

                                </fieldset>
                                
                        </div>
                        <div class="pure-u-1-4"> 
                            <div class="placeOrder">
                                <button type="submit" class="pure-button">PLACE ORDER</button>
                                <div class="summary">


                                    <table>
                                        <tr>
                                            <td><b>Subtotal:</b></td> 
                                            <td>$<%=totalPrice%></td>
                                        </tr>

                                        <tr>
                                            <td><b>Shipping:</b></td>
                                            <td>$<%=Math.floor((totalPrice * .1) * 100) / 100%></td>
                                        </tr>
                                        <%
                                            if(discount>0){
                                                totalPrice = (totalPrice + (totalPrice * .1)) * discount;
                                            }else{
                                                totalPrice = (totalPrice + (totalPrice * .1));
                                            }
                                            
                                            totalPrice = Math.floor(totalPrice * 100) / 100;
                                        %>
                                        <tr>
                                            <td><b>Discount:</b></td>
                                            <td><%=discountFormat%></td>
                                        </tr>
                                        <tr>
                                            <td><h3>Total Price:</h3></td>
                                            <td><h3>$<%=totalPrice%></h3></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="pure-u-1-8"> 
            </div>
        </div>
    </body>
</html>
