<%@page import="entity.Payment"%>
<%@page import="entity.PaymentDAO"%>
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
        <script src="scripts/login_registerscript.js"></script>
        <style media="screen" type="text/css">

        </style>
    </head>
    <body>



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
                        <li class="pure-menu-item"><a href="my_accountName.jsp" class="pure-menu-link">Name</a></li>
                        <li class="pure-menu-item"><a href="my_accountEmail.jsp" class="pure-menu-link">Email</a></li>
                        <li class="pure-menu-item"><a href="my_accountPhone.jsp" class="pure-menu-link">Phone</a></li>                                
                        <li class="pure-menu-item"><a href="my_accountAddress.jsp" class="pure-menu-link">Address</a></li>

                        <li class="pure-menu-item menu-item-divided pure-menu-selected">
                            <a href="my_accountPayment.jsp" class="pure-menu-link">Payment</a>
                        </li>

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
                    PAYMENT
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
                    String creditCardID = "999" + userID.substring(2);
                    //int creditCardID = Integer.parseInt(creditCardIDString);

                    PaymentDAO db = new PaymentDAO();
                    Payment payment = db.getPaymentByID(creditCardID);

                    String expF = payment.getExp();
                    String exp = expF.substring(5,7) + "/" + expF.substring(2,4);

                %>
                <div class="pure-g">
                    <div class="pure-u-1-3"></div>    
                    <div class="pure-u-1-3"> 
                        <div class="header">
                            Edit your payment info using the fields below
                            Use only digits for your card number and use MM/YY format for the expiration date
                        </div>
                        <form class="pure-form" name="updatePayment" method="post" action="myaccountpayment">
                            <fieldset class="pure-group">
                                <input type="text" class="pure-input-1" placeholder="<%=payment.getNum()%>" name="num" onkeyup="checkCard(this)" onclick="checkCard(this)" onchange="checkCard(this)">
                                <input type="text" class="pure-input-1" placeholder="<%=exp%>" name="exp" onkeyup="checkExp(this)" onclick="checkExp(this)" onchange="checkExp(this)">
                                <select id="select" class="pure-input-1" name= "type">
                                    <option disabled selected value><%=payment.getType()%></option>
                                    <option>Visa</option>
                                    <option>MasterCard</option>
                                    <option>American Express</option>
                                    <option>Discover</option>
                                    <option>Capital One</option>
                                </select>

                                <input type="hidden" name="creditCardID" value="<%=creditCardID%>">
                                <input name="updatePayment" type="submit" class="pure-button login pure-input-1 pure-button-primary" value="Update">
                            </fieldset>
                        </form>
                    </div>
                    <div class="pure-u-1-3"></div>  
                </div>
            </div>
        </div>

        <script src="scripts/my_account.js"></script>

    </body>
</html>