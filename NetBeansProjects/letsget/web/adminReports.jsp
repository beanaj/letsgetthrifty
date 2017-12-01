<%-- 
    Document   : reports
    Created on : Nov 30, 2017, 6:15:32 PM
    Author     : andrewjacobsen
--%>

<%@page import="entity.PublisherSales"%>
<%@page import="entity.BookSales"%>
<%@page import="entity.Book"%>
<%@page import="entity.LowInventory"%>
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
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
        <script>
            $(window).load(function () {
                // Animate loader off screen
                $(".se-pre-con").fadeOut("slow");
                ;
            });
        </script>
        <style>
            /* Paste this css to your style sheet file or under head tag */
            /* This only works with JavaScript, 
            if it's not present, don't show loader */
            .no-js #loader { display: none;  }
            .js #loader { display: block; position: absolute; left: 100px; top: 0; }
            .se-pre-con {
                position: fixed;
                left: 0px;
                top: 0px;
                width: 100%;
                height: 100%;
                z-index: 9999;
                background: url(img/Preloader_1.gif) center no-repeat #fff;
            </style>
            }



        </head>

        <body>
            <div class="se-pre-con"></div>
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
                        <a class="pure-menu-heading" href="#">ADMIN</a>
                        <ul class="pure-menu-list">
                            <li class="pure-menu-item"><a href="signout" class="pure-menu-link">Sign Out</a></li>
                            <li class="pure-menu-item"><a href="adminInventory.jsp" class="pure-menu-link">Inventory</a></li>
                            <li class="pure-menu-item"><a href="adminPromotions.jsp" class="pure-menu-link">Promotions</a></li>
                            <li class="pure-menu-item"><a href="adminUsers.jsp" class="pure-menu-link">Users</a></li>
                            <li class="pure-menu-item"><a href="adminEmployees.jsp" class="pure-menu-link">Employees</a></li>
                            <li class="pure-menu-item"><a href="adminAgencies.jsp" class="pure-menu-link">Agencies</a></li>
                            <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="adminReports.jsp" class="pure-menu-link">Agencies</a></li>
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
                    <%
                        //this report should display isbn and title of books below and close to below their thresholds
                        LowInventory li = report.generateLIR();
                    %>
                    <div class="lir">
                        <div class="lirhead">Low-Inventory Report</div>
                        <h4>This report shows the books in inventory that are close to or below their restock threshold.</h4>
                        <table>
                            <tr>
                                <th class="lhs"><br></th>
                                <th class="rhs">Threshold</th>
                                <th class="rhs">QTY</th>
                            </tr>
                            <%
                                Book[] books = li.getBooks();
                                for (int i = 0; i < books.length; i++) {
                                    Book book = books[i];
                                    String classtype = book.getPicture();
                            %>
                            <tr>
                                <th class="lhs"><%=book.getTitle()%><br></th>
                                <th class="rhs"><%=book.getMinThreshold()%></th>
                                <th class="<%=classtype%>"><%=book.getQtyInStock()%></th>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>
                    <br>
                    <%
                        //this report should show all books sold and the number of them and their total sale and profit
                        BookSales bsr = new BookSales();
                        bsr = report.generateBSR();
                    %>
                    <div class="bsr">
                        <div class="bsrhead">Book Sales Report</div> 
                        <h4>This report shows the overall sales of books in the system.</h4>
                        <table>
                            <tr>
                                <th class="lhs"><br></th>
                                <th class="rhs"></th>
                                <th class="rhs">Sold</th>
                                <th class="rhs">Revenue</th>
                                <th class="rhs">Cost</th>
                                <th class="rhs">Profit</th>

                            </tr>
                            <%
                                books = bsr.getBooks();
                                for (int i = 0; i < books.length; i++) {
                                    Book book = books[i];
                                    double profitD = book.getRating();
                                    profitD = Math.floor((profitD * 100) / 100);
                            %>
                            <tr>
                                <th class="lhs"><%=book.getISBN()%><br></th>
                                <th class="lhs"><%=book.getTitle()%><br></th>
                                <th class="rhs"><%=book.getQtyInStock()%></th>
                                <th class="revenue">$<%=book.getSellPrice()%></th>
                                <th class="costs">$<%=book.getBuyPrice()%></th>
                                <th class="profit">$<%=profitD%></th>
                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>
                    <div class="psr">
                        <div class="psrhead">Publisher Sales Report</div>
                        <h4>This report shows the overall sales of books by publisher.</h4>
                        <%
                            PublisherSales psr = new PublisherSales();
                            psr = report.generatePSR();
                        %>
                        <table>
                            <tr>

                                <th class="rhs"></th>
                                <th class="rhs">Sold</th>
                                <th class="rhs">Revenue</th>
                                <th class="rhs">Cost</th>
                                <th class="rhs">Profit</th>

                            </tr>
                            <%
                                Book[] pubs = psr.getBooks();
                                for (int i = 0; i < pubs.length; i++) {
                                    Book pub = pubs[i];
                                    double revD = pub.getSellPrice();
                                    revD = Math.floor((revD * 100) / 100);
                                    double costD = pub.getBuyPrice();
                                    costD = Math.floor((costD * 100) / 100);
                                    double profitD = pub.getRating();
                                    profitD = Math.floor((profitD * 100) / 100);
                            %>
                            <tr>
                                <th class="lhs"><%=pub.getTitle()%><br></th>
                                <th class="rhs"><%=pub.getQtyInStock()%></th>
                                <th class="revenue">$<%=revD%></th>
                                <th class="costs">$<%=costD%></th>
                                <th class="profit">$<%=profitD%></th>
                            </tr>
                            <%
                                }
                            %>
                        </table>

                    </div>
                </div>
                <div class="pure-u-2-8"> 
                </div>
            </div>
        </body>
    </html>
