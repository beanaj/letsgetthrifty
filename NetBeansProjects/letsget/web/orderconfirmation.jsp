<%-- 
    Document   : orderconfirmation
    Created on : Nov 29, 2017, 2:00:16 PM
    Author     : andrewjacobsen
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
                }
            } else {
                sessionID = session.getId();
            }


        %>

        <!-- Menu toggle -->
        <a href="#menu" id="menuLink" class="menu-link">
            <!-- Hamburger icon -->
            <span></span>
        </a>
        <div class="pure-g">
            <div class="pure-u-1-4"> 
               
            </div>
            <div class="pure-u-2-4">
                <div class="banner">
                    <h1 class="bannerhead">
                        <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
                    </h1>
                </div>

                <div class="browseheader">
                    ORDER CONFIRMATION
                </div>
                <div class= "ordersummary">
                    <%=orderID%>
                </div>
            </div>
            <div class="pure-u-1-4"> 
            </div>
        </div>
    </body>
</html>

