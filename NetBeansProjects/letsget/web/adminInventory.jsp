<%-- 
    Document   : adminPage
    Created on : Nov 8, 2017, 2:08:17 PM
    Author     : Addison
--%>

<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="services.DatabaseUtility"%>
<%@page import="entity.BookDAO"%>
<%@page import="entity.Book"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Inventory</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/adminstyle.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">      
    </head>


    <body>
        <div class="pure-g">




            <!-- Menu toggle -->
            <a href="#menu" id="menuLink" class="menu-link">
                <!-- Hamburger icon -->
                <span></span>
            </a>



            <div class="pure-u-1-4">
                <div id="menu">
                    <div class="pure-menu">
                        <a class="pure-menu-heading" href="#">Admin</a>

                        <ul class="pure-menu-list">
                            <li class="pure-menu-item"><a href="signout" class="pure-menu-link">Sign Out</a></li>
                            <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="adminInventory.jsp" class="pure-menu-link">Inventory</a></li>
                            <li class="pure-menu-item"><a href="adminPromotions.jsp" class="pure-menu-link">Promotions</a></li>
                            <li class="pure-menu-item"><a href="adminUsers.jsp" class="pure-menu-link">Users</a></li>
                            <li class="pure-menu-item"><a href="adminEmployees.jsp" class="pure-menu-link">Employees</a></li>
                            <li class="pure-menu-item"><a href="adminAgencies.jsp" class="pure-menu-link">Agencies</a></li>
                            <li class="pure-menu-item"><a href="adminReports.jsp" class="pure-menu-link">Reports</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="pure-u-5-8">
                <div class="banner">
                    <h1 class="bannerhead">
                        <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
                    </h1>
                </div>
                <div class="browseheader">
                    INVENTORY
                </div>

                <!--THE TABLE:::-->
                <div class="tableholder">
                    <table>
                        <tr>  
                            <td><b>ISBN</b></td>
                            <td><b>Genre</b></td>
                            <td><b>Author</b></td>
                            <td><b>Title</b></td>
                            <td><b>Rating</b></td>
                            <td><b>Picture Link</b></td>
                            <td><b>Edition</b></td>
                            <td><b>Publisher</b></td>
                            <td><b>Publication Year</b></td>
                            <td><b>Quantity</b></td>
                            <td><b>Min Threshold</b></td>
                            <td><b>Buy Price</b></td>
                            <td><b>Sell Price</b></td>
                        </tr>

                        <!--Add book row:-->
                        <form name="addBookButton" method="post" action="book">
                            <tr>
                                <td><input type="text" name="new_isbn" onkeyup="checkISBN(this)" onclick="checkISBN(this)" onchange="checkISBN(this)" required></td>
                                <td><input type="text" name="new_genre" onkeyup="checkGenre(this)" onclick="checkGenre(this)" onchange="checkGenre(this)" required></td>
                                <td><input type="text" name="new_author" onkeyup="checkAuthor(this)" onclick="checkAuthor(this)" onchange="checkAuthor(this)" required></td>
                                <td><input type="text" name="new_title" onkeyup="checkTitle(this)" onclick="checkTitle(this)" onchange="checkTitle(this)" required></td>
                                <td><input type="text" name="new_rating" onkeyup="checkRating(this)" onclick="checkRating(this)" onchange="checkRating(this)" required></td>
                                <td><input type="text" name="new_picture" onkeyup="checkLink(this)" onclick="checkLink(this)" onchange="checkLink(this)" required></td>
                                <td><input type="text" name="new_edition" onkeyup="checkEdition(this)" onclick="checkEdition(this)" onchange="checkEdition(this)" required></td>
                                <td><input type="text" name="new_publisher" onkeyup="checkPublisher(this)" onclick="checkPublisher(this)" onchange="checkPublisher(this)" required></td>
                                <td><input type="text" name="new_publicationyear" onkeyup="checkPubYear(this)" onclick="checkPubYear(this)" onchange="checkPubYear(this)" required></td>
                                <td><input type="text" name="new_quantity" onkeyup="checkQuantity(this)" onclick="checkQuantity(this)" onchange="checkQuantity(this)" required></td>
                                <td><input type="text" name="new_minthreshold" onkeyup="checkThreshold(this)" onclick="checkThreshold(this)" onchange="checkThreshold(this)" required></td>
                                <td><input type="text" name="new_buyprice" onkeyup="checkBuyP(this)" onclick="checkBuyP(this)" onchange="checkBuyP(this)" required></td>
                                <td><input type="text" name="new_sellprice" onkeyup="checkSellP(this)" onclick="checkSellP(this)" onchange="checkSellP(this)" required></td>
                                <td><input name="addUpdate" type="submit" value="Add Book"></td>
                            </tr>
                        </form>

                        <!--Update Book Row:-->
                        <form name="addBookButton" method="post" action="book">
                            <tr>
                                <td><input type="text" name="new_isbn" onkeyup="checkISBN(this)" onclick="checkISBN(this)" onchange="checkISBN(this)" required></td>
                                <td><input type="text" name="new_genre" onkeyup="checkGenre(this)" onclick="checkGenre(this)" onchange="checkGenre(this)"></td>
                                <td><input type="text" name="new_author" onkeyup="checkAuthor(this)" onclick="checkAuthor(this)" onchange="checkAuthor(this)"></td>
                                <td><input type="text" name="new_title" onkeyup="checkTitle(this)" onclick="checkTitle(this)" onchange="checkTitle(this)"></td>
                                <td><input type="text" name="new_rating" onkeyup="checkRating(this)" onclick="checkRating(this)" onchange="checkRating(this)"></td>
                                <td><input type="text" name="new_picture" onkeyup="checkLink(this)" onclick="checkLink(this)" onchange="checkLink(this)"></td>
                                <td><input type="text" name="new_edition" onkeyup="checkEdition(this)" onclick="checkEdition(this)" onchange="checkEdition(this)"></td>
                                <td><input type="text" name="new_publisher" onkeyup="checkPublisher(this)" onclick="checkPublisher(this)" onchange="checkPublisher(this)"></td>
                                <td><input type="text" name="new_publicationyear" onkeyup="checkPubYear(this)" onclick="checkPubYear(this)" onchange="checkPubYear(this)"></td>
                                <td><input type="text" name="new_quantity" onkeyup="checkQuantity(this)" onclick="checkQuantity(this)" onchange="checkQuantity(this)"></td>
                                <td><input type="text" name="new_minthreshold" onkeyup="checkThreshold(this)" onclick="checkThreshold(this)" onchange="checkThreshold(this)"></td>
                                <td><input type="text" name="new_buyprice" onkeyup="checkBuyP(this)" onclick="checkBuyP(this)" onchange="checkBuyP(this)"></td>
                                <td><input type="text" name="new_sellprice" onkeyup="checkSellP(this)" onclick="checkSellP(this)" onchange="checkSellP(this)"></td>
                                <td><input name="addUpdate" type="submit" value="Update Book"></td>
                            </tr>
                        </form>

                        <tr>

                        </tr>
                        <tr>  
                            <td><b>ISBN</b></td>
                            <td><b>Genre</b></td>
                            <td><b>Author</b></td>
                            <td><b>Title</b></td>
                            <td><b>Rating</b></td>
                            <td><b>Publisher</b></td>
                            <td><b>Quantity</b></td>
                            <td><b>Min Threshold</b></td>
                            <td><b>Buy Price</b></td>
                            <td><b>Sell Price</b></td>
                        </tr>

                        <%
                            //Create a BookDAO Object to access a list of the books
                            BookDAO db = new BookDAO();
                            List<Book> bookList = db.list();

                            //Loop through the book list and put the books in a table:
                            for (int i = 0; i < bookList.size(); i++) {
                        %>


                        <%
                            String primaryKey = bookList.get(i).getISBN();
                            String deleteType = "book";
                        %>
                        <tr> 
                            <td><%=bookList.get(i).getISBN()%></td>
                            <td><%=bookList.get(i).getGenre()%></td>
                            <td><%=bookList.get(i).getAuthor()%></td>
                            <td><%=bookList.get(i).getTitle()%></td>
                            <td><%=bookList.get(i).getRating()%></td>
                            <td><%=bookList.get(i).getPublisher()%></td>
                            <td><%=bookList.get(i).getQtyInStock()%></td>
                            <td><%=bookList.get(i).getMinThreshold()%></td>
                            <td><%=bookList.get(i).getBuyPrice()%></td>
                            <td><%=bookList.get(i).getSellPrice()%></td>
                            <td>
                                <a href="deleteBook.jsp?deleteid=<%=primaryKey%>&type=<%=deleteType%>" class="delete">DELETE</a>
                            </td>
                        </tr>

                        <%
                            }
                        %>
                    </table>
                </div>
                <!--END OF TABLE-->
            </div>


            <div id="error">
                ${requestScope.error}
            </div> 

            <div class="pure-u-1-8"></div>     
        </div>
    </div>
</div>




<script src="scripts/admin.js"></script>
<script src="scripts/adminInventoryValidation.js"></script>


</body>
</html>
