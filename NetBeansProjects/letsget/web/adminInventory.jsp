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
        <div class="banner">
        <h1 class="bannerhead">
            <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
        </h1>
        </div>
        
        <div id="layout">
            
        
            
        <!-- Menu toggle -->
        <a href="#menu" id="menuLink" class="menu-link">
        <!-- Hamburger icon -->
            <span></span>
        </a>

        

    <div id="main">
            
                    
        

        <div id="menu">
        <div class="pure-menu">
            <a class="pure-menu-heading" href="#">Admin</a>

            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="homepage.jsp" class="pure-menu-link">Home</a></li>
                <li class="pure-menu-item"><a href="#" class="pure-menu-link">My Cart</a></li>
                <li class="pure-menu-item"><a href="" class="pure-menu-link">Search</a></li>
                <li class="pure-menu-item"><a href="my_accont.jsp" class="pure-menu-link">My Account</a></li>
                <li class="pure-menu-item"><a href="login_register.jsp" class="pure-menu-link">Log In/Register</a></li>
                <li class="pure-menu-item"><a href="#" class="pure-menu-link">Sign Out</a></li>
                <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="adminInventory.jsp" class="pure-menu-link">Inventory</a></li>
                <li class="pure-menu-item"><a href="adminPromotions.jsp" class="pure-menu-link">Promotions</a></li>

                <li class="pure-menu-item"><a href="adminUsers.jsp" class="pure-menu-link">Users</a></li>

                <li class="pure-menu-item"><a href="adminEmployees.jsp" class="pure-menu-link">Employees</a></li>
                <li class="pure-menu-item"><a href="adminAgencies.jsp" class="pure-menu-link">Agencies</a></li>
            </ul>
        </div>
        </div>
        
        <div class="content">
            <div class="container">               
                <div class="box">
                    <div class="header">
                        <h1>Inventory</h1>
                    </div>               
                </div>
                
                <!--THE TABLE:::-->
                
<table id="bookTable" align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr>  
    <td><b>ISBN</b></td>
    <td><b>Genre</b></td>
    <td><b>Author</b></td>
    <td><b>Title</b></td>
    <td><b>Rating</b></td>
    <td><b>Picture Link</b></td>
    <td><b>Edition</b></td>
    <td><b>Publisher</b></td>
    <td><b>Publication year</b></td>
    <td><b>Quantity</b></td>
    <td><b>Min Threshold</b></td>
    <td><b>Buy Price</b></td>
    <td><b>Sell Price</b></td>
    <td><b>Supplier ID</b></td>
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
    <td><%=bookList.get(i).getISBN() %></td>
    <td><%=bookList.get(i).getGenre() %></td>
    <td><%=bookList.get(i).getAuthor() %></td>
    <td><%=bookList.get(i).getTitle() %></td>
    <td><%=bookList.get(i).getRating() %></td>
    <td><%=bookList.get(i).getPicture() %></td>
    <td><%=bookList.get(i).getEdition() %></td>
    <td><%=bookList.get(i).getPublisher() %></td>
    <td><%=bookList.get(i).getPublicationYear() %></td>
    <td><%=bookList.get(i).getQtyInStock()%></td>
    <td><%=bookList.get(i).getMinThreshold()%></td>
    <td><%=bookList.get(i).getBuyPrice() %></td>
    <td><%=bookList.get(i).getSellPrice() %></td>
    <td><%=bookList.get(i).getSupplierID()%></td>
    <td>
        <a href="deleteBook.jsp?deleteid=<%=primaryKey%>&type=<%=deleteType%>">Delete</a>
    </td>
</tr>

<% 
}
%>

<!--Add book row:-->
<form name="addBookButton" method="post" action="book">
<tr>
    <td><input type="text" name="new_isbn" required></td>
    <td><input type="text" name="new_genre" required></td>
    <td><input type="text" name="new_author" required></td>
    <td><input type="text" name="new_title" required></td>
    <td><input type="text" name="new_rating" required></td>
    <td><input type="text" name="new_picture" required></td>
    <td><input type="text" name="new_edition" required></td>
    <td><input type="text" name="new_publisher" required></td>
    <td><input type="text" name="new_publicationyear" required></td>
    <td><input type="text" name="new_quantity" required></td>
    <td><input type="text" name="new_minthreshold" required></td>
    <td><input type="text" name="new_buyprice" required></td>
    <td><input type="text" name="new_sellprice" required></td>
    <td><input type="text" name="new_supplierID" required></td>
    <td><input name="addUpdate" type="submit" value="Add Book"></td>
</tr>
</form>

<!--Update Book Row:-->
<form name="addBookButton" method="post" action="book">
    <tr>
    <td><input type="text" name="new_isbn" required></td>
    <td><input type="text" name="new_genre"></td>
    <td><input type="text" name="new_author"></td>
    <td><input type="text" name="new_title"></td>
    <td><input type="text" name="new_rating"></td>
    <td><input type="text" name="new_picture"></td>
    <td><input type="text" name="new_edition"></td>
    <td><input type="text" name="new_publisher"></td>
    <td><input type="text" name="new_publicationyear"></td>
    <td><input type="text" name="new_quantity"></td>
    <td><input type="text" name="new_minthreshold"></td>
    <td><input type="text" name="new_buyprice"></td>
    <td><input type="text" name="new_sellprice"></td>
    <td><input type="text" name="new_supplierID"></td>
    <td><input name="addUpdate" type="submit" value="Update Book"></td>
    </tr>
</form>

</table>       
<!--END OF TABLE-->
               

            </div>      
        </div>
    </div>
</div>




<script src="scripts/admin.js"></script>


    </body>
</html>
