<%-- 
    Document   : adminPage
    Created on : Nov 8, 2017, 2:08:17 PM
    Author     : Addison
--%>

<%@page import="java.util.List"%>
<%@page import="entity.User"%>
<%@page import="entity.UserDAO"%>
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
        <title>Admin Users</title>
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

                <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="adminUsers.jsp" class="pure-menu-link">Users</a></li>

                <li class="pure-menu-item"><a href="adminEmployees.jsp" class="pure-menu-link">Employees</a></li>
                <li class="pure-menu-item"><a href="adminAgencies.jsp" class="pure-menu-link">Agencies</a></li>
            </ul>
        </div>
        </div>
        
        <div class="content">
            <div class="container">               
                <div class="box">
                    <div class="header">
                        <h1>Users</h1>
                    </div>               
                </div>
                
                <!--THE TABLE:::-->
<table id="bookTable" align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>

<tr>
<td><b>User ID</b></td>
<td><b>First Name</b></td>
<td><b>Last Name</b></td>
<td><b>Phone</b></td>
<td><b>Email</b></td>
<td><b>User Type</b></td>
<td><b>Order Confirmation</b></td>
<td><b>Active</b></td>
</tr>

<%
    UserDAO db = new UserDAO();
    List<User> userList = db.list();
    
    for (int i = 0; i < userList.size(); i++) {
%>

<tr>  
    <%
        String primaryKey = userList.get(i).getAccountID();
    %>
<td><%=userList.get(i).getAccountID() %></td>
<td><%=userList.get(i).getFN() %></td>
<td><%=userList.get(i).getLN()%></td>
<td><%=userList.get(i).getPhone()%></td>
<td><%=userList.get(i).getEmail()%></td>
<td><%=userList.get(i).getType()%></td>
<td><%=userList.get(i).getCode()%></td>
<td><%=userList.get(i).getActive()%></td>
<td>
    <!--Promote User-->
    <form name="promoteUserButton" method="post" action="userTable">
        <input type="hidden" name="new_userID" value="<%=primaryKey%>">
        <input name="addUpdate" type="submit" value="Promote User">
    </form>
</td>
<td>
    <!--Suspend User-->
    <form name="suspendUserButton" method="post" action="userTable">
        <input type="hidden" name="new_userID" value="<%=primaryKey%>">
        <input name="addUpdate" type="submit" value="Suspend User">
    </form>
</td>
    
</tr>

<% 
}
%>

<!--Update User Row:-->
<form name="updateUserButton" method="post" action="userTable">
    <tr>
    <td><input type="text" name="new_userID" required></td>
    <td><input type="text" name="new_firstName"></td>
    <td><input type="text" name="new_lastName"></td>
    <td><input type="text" name="new_phone"></td>
    <td><input type="text" name="new_email"></td>
    <td><input type="text" name="new_userType"></td>
    <td><input type="text" name="new_orderConfirmationCode"></td>
    <td><input type="text" name="new_active"></td>
    <input type="hidden" name = "tableType" value="user">
    <td><input name="addUpdate" type="submit" value="Update User"></td>
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

