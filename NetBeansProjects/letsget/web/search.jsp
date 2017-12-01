<%-- 
    Document   : search
    Created on : Nov 5, 2017, 12:09:10 PM
    Author     : Ian
--%>
<!DOCTYPE html>
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
        <link rel="stylesheet" href="styles/search.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
       
        <script src="scripts/searchvalidation.js"></script>
        
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
            Connection con = null;
            Statement state = null;
            ResultSet result = null;
            DatabaseUtility db = new DatabaseUtility();
        %>
        <div class="banner">
            <h1 class="bannerhead">
                <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
            </h1>
        </div>
        <!-- Menu toggle -->
        <a href="#menu" id="menuLink" class="menu-link">
            <!-- Hamburger icon -->
            <span></span>
        </a>
        <div class="pure-g">
            <div class="pure-u-1-4"> 
                <div id ="menu" class="pure-menu">
                    <a class="pure-menu-heading" href="#">Welcome <%="\n " + userName%></a>
                    <ul class="pure-menu-list">
                        <li class="pure-menu-item"><a href="homepage.jsp" class="pure-menu-link">Home</a></li>
                        <li class="pure-menu-item"><a href="my_cart.jsp" class="pure-menu-link">My Cart</a></li>
                        <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="" class="pure-menu-link">Search</a></li>
                        <li class="pure-menu-item"><a href="my_accountName.jsp" class="pure-menu-link">My Account</a></li>
                        <li class="pure-menu-item"><a href="login_register.jsp" class="pure-menu-link">Log In/Register</a></li>
                        <li class="pure-menu-item"><a href="#" class="pure-menu-link">Sign Out</a></li>
                    </ul>
                </div>
            </div>
            <div class="pure-u-5-8">
                <div class="search">
                     <form class="pure-form" method="post" action="searchresult">
                        <input class="searchbar" name ="input" onchange="searchBar(this)" placeholder="Search by Title, Author, Genre, or ISBN">
                        <select class="searchOption" name="option">
                            <option value="title">Title</option>
                            <option value="author">Author</option>
                            <option value="genre">Genre</option>
                            <option value="ISBN">ISBN</option>
                        </select>
                        <input type="submit" name="button" class="pure-button pure-button-primary" value="SHOW ME WHAT YOU GOT">
                    </form>
                </div>
                <div class="browseheader">
                    CATALOG
                </div>
        <table>
            <%try {
                //register the driver
                Class.forName(db.getDriver());
                //connect to the database
                con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
                //generate sql statement
                state = con.createStatement();
                String sql = "SELECT * FROM books ORDER BY title";
                result = state.executeQuery(sql);
                while(result.next()){%>
                    <tr>  
                    <%
                        String primaryKey = result.getString("isbn");
                        String pic = result.getString("picture");
                    %>
                    <td><image src ="<%=pic%>" height="200" width=130"></td>    
                    <td><h3><%=result.getString("title")%></h3>
                        <b><%=result.getString("author")%></b><br><br>
                        <%=result.getString("genre")%><br>
                        <%=result.getString("rating")%>/5</td>
                    <td><h4>$<%=result.getString("sellPrice")%></h4><br>
                        <form class="pure-form" method = "post" action = "cartmanager">
                            <input type="hidden" name="isbn" value=<%=primaryKey%>>
                            <input type="hidden" name="quantity" value="1">
                            <input type="hidden" name="update" value="false">
                            <input type="hidden" name="price" value=<%=result.getString("sellPrice")%>>
                            <button type="submit" class="pure-button">ADD TO CART</button>
                        </form>
                </tr>
                <%}
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
            con.close();
            %>
            
        </table> 
            </div>
            <div class="pure-u-1-8"> 
        </div>
        </div>
    </body>
</html>
