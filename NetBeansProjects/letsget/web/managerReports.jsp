
<%@page import="entity.EndOfDaySales"%>
<%@page import="entity.Report"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="services.DatabaseUtility"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Reports</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
        <!--[if lte IE 8]>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-old-ie-min.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
        <!--<![endif]-->
        <link rel="stylesheet" href="styles/reportstyle.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style src="hompagestyle.css"></style>
    </head>
    <body>
        <%
            String userID = null;
            userID = (String) session.getAttribute("userID");
            String userName = "Guest";
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

        <!-- Menu toggle -->
        <a href="#menu" id="menuLink" class="menu-link">
            <!-- Hamburger icon -->
            <span></span>
        </a>
        <div class="pure-g">
            <div class="pure-u-1-4"> 
                <div id ="menu" class="pure-menu">
                    <a class="pure-menu-heading" href="#">Manager</a>
                    <ul class="pure-menu-list">
                        <li class="pure-menu-item"><a href="signout" class="pure-menu-link">Sign Out</a></li>
                    </ul>
                </div>
            </div>
            <div class="pure-u-1-2" >
                <div class="banner">
                    <h1 class="bannerhead">
                        <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
                    </h1>
                </div>

                <div class="reportheader">
                    REPORTS
                </div>
                <br>
                <div class="eod">
                <div class="eodhead">End of Day Sales Report</div> 
                <h4>This report shows the current sales statistics of the day.</h4>
                <%                    //this report should show the number of orders places
                    //the amount of money made in total
                    //the cost of the books to us?
                    //total profit
                    //first thing i need to do is get an array with all these statistics, produced by a report class
                    Report report = new Report();
                    EndOfDaySales eod = report.generateEODS();
                %>

                <table>
                    <tr>
                        <th class="lhs">Total Orders Placed:<br></th>
                        <th class="rhs"><%=eod.getTotalOrders()%></th>
                    </tr>
                    <tr>
                        <th class="lhs">Total Books Sold:<br></th>
                        <th class="rhs"><%=eod.getTotalBooksSold()%></th>
                    </tr>
                    <tr>
                        <th class="lhs">Total Visa Sales:<br></th>
                        <th class="rhs"><%=eod.getVisaSales()%></th>
                    </tr>
                    <tr>
                        <th class="lhs">Total MasterCard Sales:<br></th>
                        <th class="rhs"><%=eod.getMasterCardSales()%></th>
                    </tr>
                    <tr>
                        <th class="lhs">Total American Express Sales:<br></th>
                        <th class="rhs"><%=eod.getAmericanExpressSales()%></th>
                    </tr>
                    <tr>
                        <th class="lhs">Total Discover Sales:<br></th>
                        <th class="rhs"><%=eod.getDiscoverSales()%></th>
                    </tr>
                    <tr>
                        <th class="lhs">Total CapitalOne Sales:<br></th>
                        <th class="rhs"><%=eod.getCapitalOneSales()%></th>
                    </tr>
                    <tr>
                        <th class="lhs">Total Revenue:<br></th>
                        <th class="revenue">$<%=eod.getTotalRevenue()%></th>
                    </tr>
                    <tr>
                        <th class="lhs">Total Costs:<br></th>
                        <th class="costs">$<%=eod.getTotalCosts()%></th>
                    </tr>
                    <tr>
                        <th class="lhs">Total Profit:<br></th>
                        <th class="profit">$<%=eod.getTotalProfit()%></th>
                    </tr>
                </table>
                
                </div>
                        <br>
                <div class="lirhead">Low-Inventory Report</div>
                <%
                    //this report should display isbn and title of books below and close to below their thresholds
                %>
                <div class="bsrhead">Book Sales Report</div>  
                <%
                    //this report should show all books sold and the number of them and their total sale and profit
                %>
                <div class="psrhead">Publisher Sales Report</div>
                <%                    //this report should show the number of books each publisher has sold, their total sales and profit
%>



            </div>
            <div class="pure-u-2-8"> 
            </div>
        </div>
        <script src="scripts/homepage.js"></script>
    </body>
</html>
