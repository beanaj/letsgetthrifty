<%@page import="entity.Order"%>
<%@page import="java.util.List"%>
<%@page import="entity.OrderDAO"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>My Account</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
        <!--[if lte IE 8]>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-old-ie-min.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
        <!--<![endif]-->
        <link rel="stylesheet" href="styles/my_account.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--<script src="scripts/login_registerscript.js"></script>-->
        <style media="screen" type="text/css">

        </style>
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
        %>



        <div id="layout">
            <!-- Menu toggle -->
            <a href="#menu" id="menuLink" class="menu-link">
                <!-- Hamburger icon -->
                <span></span>
            </a>

            <div id="menu">
                <div class="pure-menu">
                    <a class="pure-menu-heading" href="#">Welcome!</a>

                    <ul class="pure-menu-list">
                        <li class="pure-menu-item"><a href="homepage.jsp" class="pure-menu-link">Home</a></li>
                        <li class="pure-menu-item"><a href="my_cart.jsp" class="pure-menu-link">My Cart</a></li>
                        <li class="pure-menu-item"><a href="search.jsp" class="pure-menu-link">Search</a></li>

                        <li class="pure-menu-item menu-item-divided pure-menu-selected">
                            <a href="my_accountName.jsp" class="pure-menu-link">My Account</a>
                        </li>
                    </ul>
                    <a class="pure-menu-heading" href="#">My Account</a>
                    <ul class="pure-menu-list">
                        <li class="pure-menu-item"><a href="my_accountName.jsp" class="pure-menu-link">Name</a></li>
                        <li class="pure-menu-item"><a href="my_accountEmail.jsp" class="pure-menu-link">Email</a></li>
                        <li class="pure-menu-item"><a href="my_accountPhone.jsp" class="pure-menu-link">Phone</a></li>                                
                        <li class="pure-menu-item"><a href="my_accountAddress.jsp" class="pure-menu-link">Address</a></li>
                        <li class="pure-menu-item"><a href="my_accountPayment.jsp" class="pure-menu-link">Payment</a></li>                
                        <li class="pure-menu-item"><a href="my_accountPassword.jsp" class="pure-menu-link">Password</a></li>
                        <li class="pure-menu-item"><a href="my_accountPromotion.jsp" class="pure-menu-link">Promotion</a></li>

                        <li class="pure-menu-item menu-item-divided pure-menu-selected">
                            <a href="my_accountOrders.jsp" class="pure-menu-link">Orders</a>
                        </li>

                    </ul>
                </div>
            </div>

            <div id="main">
                <div class="banner">
                    <h1 class="bannerhead">
                        <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
                    </h1>
                </div>        

                <div class="content">
                    <div class="container">               
                        <div class="box">
                            <div class="header">
                                <h1>Orders</h1>
                            </div>               
                        </div>

                        <!--THE TABLE:::-->
                        <table id="orderTable" align="center" cellpadding="5" cellspacing="5" border="1">
                            <tr>

                            </tr>
                            <td><b>Order ID</b></td>
                            <td><b>Shipping Agency ID</b></td>
                            <td><b>Order Status</b></td>
                            <td><b>Order Date</b></td>
                            <td><b>Shipping Address</b></td>
                            <td><b>Billing Address</b></td>
                            <td><b>Payment Method</b></td>
                            <td><b>Confirmation Number</b></td>
                            <td><b>User ID</b></td>
                            <td><b>Order Total</b></td>
                            <td><b>Credit Card ID</b></td>
                            </tr>

                            <%
                                OrderDAO db = new OrderDAO();
                                //make an array of the user's orders:
                                List<Order> orders = db.getUserOrders(userID);

                                for (int i = 0; i < orders.size(); i++) {
                            %>

                            <tr>  
                                <%
                                    int primaryKey = orders.get(i).getOrderID();

                                %>
                                <td><%=orders.get(i).getOrderID()%></td>
                                <td><%=orders.get(i).getShippingAgencyID()%></td>
                                <td><%=orders.get(i).getOrderStatus()%></td>
                                <td><%=orders.get(i).getOrderDate()%></td>
                                <td><%=orders.get(i).getShippingAddress()%></td>
                                <td><%=orders.get(i).getBillingAddress()%></td>
                                <td><%=orders.get(i).getPaymentMethod()%></td>
                                <td><%=orders.get(i).getConfirmationNumber()%></td>
                                <td><%=orders.get(i).getUserID()%></td>
                                <td><%=orders.get(i).getOrderTotal()%></td>
                                <td><%=orders.get(i).getCreditCardID()%></td>

                            </tr>

                            <%
                                }
                            %>

                        </table>       
                        <!--END OF TABLE-->


                        <div id="error">
                            ${requestScope.error}
                        </div>  

                    </div>      
                </div>

            </div>
        </div>

        <script src="scripts/my_account.js"></script>

    </body>
</html>
