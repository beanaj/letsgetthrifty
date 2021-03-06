<%-- 
    Document   : homepage
    Created on : Nov 5, 2017, 12:07:53 PM
    Author     : Ian
--%>
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
        <title>Let's Get Thrifty</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
        <!--[if lte IE 8]>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-old-ie-min.css">
        <![endif]-->
        <!--[if gt IE 8]><!-->
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/grids-responsive-min.css">
        <!--<![endif]-->
        <link rel="stylesheet" href="styles/homepagestyle.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style src="hompagestyle.css"></style>
    </head>
    <body>
        <%  if (session.getAttribute("type") != null) {
                if (session.getAttribute("type").equals("a")) {
                    response.sendRedirect("adminInventory.jsp");
                } else if (session.getAttribute("type").equals("e")) {
                    response.sendRedirect("shipmentEmployee.jsp");
                } else if (session.getAttribute("type").equals("m")) {
                    response.sendRedirect("managerReports.jsp");
                }
            }
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
            Connection con = null;
            Statement state = null;
            ResultSet result = null;
            DatabaseUtility db = new DatabaseUtility();
            String booksInCart = "";
            try {
                //register the driver
                Class.forName(db.getDriver());
                //connect to the database
                con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
                //generate sql statement
                state = con.createStatement();
                String sql = "SELECT * FROM carts WHERE userID = '" + userID + "'";
                result = state.executeQuery(sql);
                int inCart = 0;
                while (result.next()) {
                    inCart++;
                }

                if (inCart > 0) {
                    booksInCart = " ( " + Integer.toString(inCart) + " )";
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
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
                    <a class="pure-menu-heading" href="#">Welcome<%="\n " + userName%></a>
                    <ul class="pure-menu-list">
                        <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="homepage.jsp" class="pure-menu-link">Home</a></li>
                        <li class="pure-menu-item"><a href="my_cart.jsp" class="pure-menu-link">My Cart<%="" + booksInCart%></a></li>
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
                <div class="search">
                    <form class="pure-form"method="post" action="searchresult">
                        <div class="pure-g">
                            <div class="pure-u-3-4"> 
                                <input class="searchbar" name="input" type="search" placeholder="Search for books">
                            </div>
                            <div class="pure-u-1-4">     
                                <button type="submit" class="pure-button pure-button-primary" id="searchbutton">SEARCH</button>
                            </div>
                    </form>
                </div>
            </div>
            <div class="browseheader">
                RECENT ADDITIONS
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
                    try {
                        connection = DriverManager.getConnection(connectionUrl, userId, password);
                        statement = connection.createStatement();
                        String sql = "SELECT * FROM books";

                        resultSet = statement.executeQuery(sql);
                        for (int i = 0; i < 15; i++) {
                            resultSet.next();
                %>
                <tr>  
                    <%
                        String primaryKey = resultSet.getString("isbn");
                        String pic = resultSet.getString("picture");
                    %>
                    <td><image src ="<%=pic%>" height="200" width=130"></td>    
                    <td><h3><%=resultSet.getString("title")%></h3>
                        <b><%=resultSet.getString("author")%></b><br><br>
                        <%=resultSet.getString("genre")%><br>
                        <%=resultSet.getString("rating")%>/5</td>
                    <td><h4>$<%=resultSet.getString("sellPrice")%></h4><br>
                        <form class="pure-form" method = "post" action = "cartmanager">
                            <input type="hidden" name="isbn" value=<%=primaryKey%>>
                            <input type="hidden" name="quantity" value="1">
                            <input type="hidden" name="update" value="false">
                            <input type="hidden" name="price" value=<%=resultSet.getString("sellPrice")%>>
                            <button type="submit" class="pure-button">ADD TO CART</button>
                        </form>
                </tr>

                <%
                        }
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                %>
            </table>       
            <!--END OF TABLE-->
        </div>
        <div class="pure-u-1-8"> 
        </div>
    </div>
    <script src="scripts/homepage.js"></script>
</body>
</html>
