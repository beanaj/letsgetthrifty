<!DOCTYPE html>

<%@page import="java.util.List"%>
<%@page import="entity.User"%>
<%@page import="entity.UserDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="services.DatabaseUtility"%>

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
        <script src="scripts/my_account.js"></script>
        <style media="screen" type="text/css">

        </style>
    </head>
    <body>

        <%
            if (session.getAttribute("type") == null) {
                response.sendRedirect("login_register.jsp");
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

                    <ul class="pure-menu-list">
                        <li class="pure-menu-item menu-item-divided pure-menu-selected">
                            <a href="my_accountName.jsp" class="pure-menu-link">Name</a>
                        </li>
                        <li class="pure-menu-item"><a href="my_accountEmail.jsp" class="pure-menu-link">Email</a></li>
                        <li class="pure-menu-item"><a href="my_accountPhone.jsp" class="pure-menu-link">Phone</a></li>
                        <li class="pure-menu-item"><a href="my_accountAddress.jsp" class="pure-menu-link">Address</a></li>
                        <li class="pure-menu-item"><a href="my_accountPayment.jsp" class="pure-menu-link">Payment</a></li>
                        <li class="pure-menu-item"><a href="my_accountPassword.jsp" class="pure-menu-link">Password</a></li>
                        <li class="pure-menu-item"><a href="my_accountPromotion.jsp" class="pure-menu-link">Promotion</a></li>                
                        <li class="pure-menu-item"><a href="my_accountOrders.jsp" class="pure-menu-link">Orders</a></li>
                    </ul>
                </div>
            </div>

            <div id="main">
                <div class="banner">
                    <h1 class="bannerhead">
                        <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
                    </h1>
                </div>
                <div class="browseheader">
                    NAME
                </div>

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
                    String userID = (String) session.getAttribute("userID");

                    UserDAO db = new UserDAO();
                    User user = db.getUser(userID, "u");

                %>
                <div class="pure-g">

                    <div class="pure-u-1-3"></div>    
                    <div class="pure-u-1-3"> 
                        <div class="header">
                            Edit your name using the fields below
                        </div>
                        <form class="pure-form" name="updateName" method="post" action="myaccountname">
                            <fieldset class="pure-group">
                                <input type="text" class="pure-input-1" placeholder="<%=user.getFN()%>" name="firstName" onkeyup="checkFN(this)" onclick="checkFN(this)" onchange="checkFN(this)">
                                <input type="text" class="pure-input-1" placeholder="<%=user.getLN()%>" name="lastName" onkeyup="checkLN(this)" onclick="checkLN(this)" onchange="checkLN(this)">
                                <input type="hidden" name="userID" value="<%=userID%>">
                                <input name="updateName" type="submit" class="pure-button pure-input-1 pure-button-primary" value="Update">
                            </fieldset>
                        </form>
                    </div> 
                    <div class="pure-u-1-3"></div>  
                </div>

                <div id="error">
                    ${requestScope.error}
                </div>  


            </div>
        </div>

        <script src="scripts/my_account.js"></script>

    </body>
</html>