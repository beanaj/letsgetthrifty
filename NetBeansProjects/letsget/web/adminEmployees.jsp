<%-- 
    Document   : adminPage
    Created on : Nov 8, 2017, 2:08:17 PM
    Author     : Addison
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="services.DatabaseUtility"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Employees</title>
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
                <li class="pure-menu-item"><a href="adminInventory.jsp" class="pure-menu-link">Inventory</a></li>
                <li class="pure-menu-item"><a href="adminPromotions.jsp" class="pure-menu-link">Promotions</a></li>

                <li class="pure-menu-item"><a href="adminUsers.jsp" class="pure-menu-link">Users</a></li>

                <li class="pure-menu-item  menu-item-divided pure-menu-selected"><a href="adminEmployees.jsp" class="pure-menu-link">Employees</a></li>
                <li class="pure-menu-item"><a href="adminAgencies.jsp" class="pure-menu-link">Agencies</a></li>
            </ul>
        </div>
        </div>
        
        <div class="content">
            <div class="container">               
                <div class="box">
                    <div class="header">
                        <h1>Employees</h1>
                    </div>               
                </div>
                
                <!--THE TABLE:::-->
                <%
DatabaseUtility db = new DatabaseUtility();

try {
Class.forName(db.getDriver());
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>

<table id="bookTable" align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<!--<tr bgcolor="#A52A2A">-->
<tr>
<td><b>User ID</b></td>
<td><b>First Name</b></td>
<td><b>Last Name</b></td>
<td><b>Phone</b></td>
<td><b>Email</b></td>
<td><b>Payment Info</b></td>
<td><b>User Type</b></td>
<td><b>User Password</b></td>
<!--<td><b>Order Confirmation</b></td>-->
<td><b>Active</b></td>
</tr>

<%
try{ 
connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
statement=connection.createStatement();
String sql ="SELECT * FROM users";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<!--<tr bgcolor="#DEB887">-->
<tr>  
    <%
        String primaryKey = resultSet.getString("userID");
    %>
<td><%=resultSet.getString("userID") %></td>
<td><%=resultSet.getString("firstName") %></td>
<td><%=resultSet.getString("lastName") %></td>
<td><%=resultSet.getString("phone") %></td>
<td><%=resultSet.getString("email") %></td>
<td><%=resultSet.getString("paymentInfo") %></td>
<td><%=resultSet.getString("userType") %></td>
<td><%=resultSet.getString("userPassword") %></td>
<!--ORDER CONFIRMATION-->
<td><%=resultSet.getString("active") %></td>
<!--<td>
    <a href="deleteBook.jsp?deleteid=<%=primaryKey%>">Delete</a>
</td>-->

</tr>

<% 
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>

<!--<tr>
    <td><input type="text" id="new_promoID"></td>
    <td><input type="text" id="new_promoName"></td>
    <td><input type="text" id="new_percentage"></td>
    <td><input type="text" id="new_expiration"></td>
    <td><input type="button" onclick="addBook()" value="Add Book"></td>
</tr>-->

</table>       
<!--END OF TABLE-->
               

            </div>      
        </div>
    </div>
</div>




<script src="scripts/admin.js"></script>


    </body>
</html>
