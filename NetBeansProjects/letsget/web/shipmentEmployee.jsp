<%-- 
    Document   : shipmentEmployee
    Created on : Nov 25, 2017, 3:22:12 PM
    Author     : Addison
--%>

<%@page import="entity.Order"%>
<%@page import="entity.OrderDAO"%>
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
    <td><b>Update Order Status</b></td>
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
//Create a OrderDAO Object to access a list of the orders
OrderDAO db = new OrderDAO();
List<Order> orderList = db.list();

//Loop through the book list and put the books in a table:
for (int i = 0; i < orderList.size(); i++) {
%>

 
    <%
        int primaryKey = orderList.get(i).getOrderID();
        String deleteType = "order";
    %>
<tr> 
    <form name="updateOrder" method="post" action="order">
    <td><%=orderList.get(i).getOrderID()%></td>
    <td><%=orderList.get(i).getShippingAgencyID()%></td> 
    <td ALIGN="center">
       <select name="orderStatus">        
            <option value="ordered">Ordered</option>
            <option value="preparing for shipment">Preparing For Shipment</option>
            <option value="shipped">Shipped</option>
            <option value="delivered">Delivered</option>
       </select>
    </td>      
    <td><%=orderList.get(i).getOrderStatus()%></td>
    <td><%=orderList.get(i).getOrderDate()%></td>
    <td><%=orderList.get(i).getShippingAddress()%></td>
    <td><%=orderList.get(i).getBillingAddress()%></td>
    <td><%=orderList.get(i).getPaymentMethod()%></td>
    <td><%=orderList.get(i).getConfirmationNumber()%></td>
    <td><%=orderList.get(i).getUserID()%></td>
    <td><%=orderList.get(i).getOrderTotal()%></td>
    <td><%=orderList.get(i).getCreditCardID()%></td>
    <td>
        <!--Update Order Status:-->
        
            <input  type="hidden" name="orderID" value=<%=primaryKey%>>
            <input name="update" type="submit" value="Update Status">
        
    </td>
    </form>
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
