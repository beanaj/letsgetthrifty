<%-- 
    Document   : shipmentEmployee
    Created on : Nov 25, 2017, 3:22:12 PM
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
        <title>Shipment Employee</title>
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
            <a class="pure-menu-heading" href="#">Shipment Employee</a>

            <ul class="pure-menu-list">
                <li class="pure-menu-item"><a href="#" class="pure-menu-link">Sign Out</a></li>
            </ul>
        </div>
        </div>
        
        <div class="content">
            <div class="container">               
                <div class="box">
                    <div class="header">
                        <h1>Orders</h1>
                    </div>               
                </div>
                
                <!--THE TABLE:::-->
                
<table id="bookTable" align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr>  
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

</table>       
<!--END OF TABLE-->
               

            </div>      
        </div>
    </div>
</div>




<script src="scripts/admin.js"></script>


    </body>
</html>
