<%-- 
    Document   : adminPage
    Created on : Nov 8, 2017, 2:08:17 PM
    Author     : Addison
--%>

<%@page import="java.util.List"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="services.DatabaseUtility"%>
<%@page import="entity.ShippingAgency"%>
<%@page import="entity.ShippingAgencyDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Agencies</title>
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

                <li class="pure-menu-item"><a href="adminEmployees.jsp" class="pure-menu-link">Employees</a></li>
                <li class="pure-menu-item  menu-item-divided pure-menu-selected"><a href="adminAgencies.jsp" class="pure-menu-link">Agencies</a></li>
            </ul>
        </div>
        </div>
        
        <div class="content">
            <div class="container">               
                <div class="box">
                    <div class="header">
                        <h1>Agencies</h1>
                    </div>               
                </div>
                
                <!--THE TABLE:::-->
<table id="bookTable" align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<!--<tr bgcolor="#A52A2A">-->
<tr>
<td><b>Shipping Agency ID</b></td>
<td><b>Agency Name</b></td>
<td><b>Phone</b></td>
<td><b>Contact Name</b></td>
<td><b>Contact Phone</b></td>
</tr>

<%
ShippingAgencyDAO db = new ShippingAgencyDAO();
List<ShippingAgency> agencyList = db.list();

for (int i = 0; i < agencyList.size(); i++) {
%>
<!--<tr bgcolor="#DEB887">-->
<tr>  
    <%
        int primaryKey = agencyList.get(i).getAgencyID();
        String deleteType = "agency";
    %>
    <td><%=agencyList.get(i).getAgencyID() %></td>
<td><%=agencyList.get(i).getAgencyName() %></td>
<td><%=agencyList.get(i).getPhone() %></td>
<td><%=agencyList.get(i).getContactName() %></td>
<td><%=agencyList.get(i).getContactPhone() %></td>
<td>
    <a href="deleteBook.jsp?deleteid=<%=primaryKey%>&type=<%=deleteType%>">Delete</a>
</td>

</tr>

<% 
}
%>

<!--Add Agency row:-->
<form name="addAgencyForm" method="post" action="agency"">
<tr>
    <td><input type="text" name="new_shippingAgencyID" onkeyup="checkID(this)" onclick="checkID(this)" onchange="checkID(this)" required></td>
    <td><input type="text" name="new_agencyName" onkeyup="checkName(this)" onclick="checkName(this)" onchange="checkName(this)" required></td>
    <td><input type="text" name="new_phone" onkeyup="checkPhone(this)" onclick="checkPhone(this)" onchange="checkPhone(this)" required></td>
    <td><input type="text" name="new_contactName" onkeyup="checkName(this)" onclick="checkName(this)" onchange="checkName(this)" required></td>
    <td><input type="text" name="new_contactPhone" onkeyup="checkPhone(this)" onclick="checkPhone(this)" onchange="checkPhone(this)" required></td>
    <td><input name="addUpdate" type="submit" value="Add Agency"></td>
</tr>
</form>


<!--Update Agency Row:-->
<form name="addAgencyButton" method="post" action="agency">
    <tr>
    <td><input type="text" name="new_shippingAgencyID" onkeyup="checkID(this)" onclick="checkID(this)" onchange="checkID(this)" required></td>
    <td><input type="text" name="new_agencyName" onkeyup="checkName(this)" onclick="checkName(this)" onchange="checkName(this)"></td>
    <td><input type="text" name="new_phone" onkeyup="checkPhone(this)" onclick="checkPhone(this)" onchange="checkPhone(this)"></td>
    <td><input type="text" name="new_contactName" onkeyup="checkName(this)" onclick="checkName(this)" onchange="checkName(this)"></td>
    <td><input type="text" name="new_contactPhone" onkeyup="checkPhone(this)" onclick="checkPhone(this)" onchange="checkPhone(this)"></td>
    <td><input name="addUpdate" type="submit" value="Update Agency"></td>
    </tr>
</form>

</table>       
<!--END OF TABLE-->
          
<div id="error">
    ${requestScope.error}
</div>     

            </div>      
        </div>
    </div>
</div>




<script src="scripts/admin.js"></script>
<script src="scripts/adminAgencyValidation.js"></script>


    </body>
</html>