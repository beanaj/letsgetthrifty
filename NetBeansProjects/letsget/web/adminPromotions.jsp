<%-- 
    Document   : adminPage
    Created on : Nov 8, 2017, 2:08:17 PM
    Author     : Addison
--%>

<%@page import="entity.Promotion"%>
<%@page import="java.util.List"%>
<%@page import="entity.PromotionDAO"%>
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
        <title>Admin Promotions</title>
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
                <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="adminPromotions.jsp" class="pure-menu-link">Promotions</a></li>

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
                        <h1>Promotions</h1>
                    </div>               
                </div>
                
                <!--THE TABLE:::-->

<table id="bookTable" align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<!--<tr bgcolor="#A52A2A">-->
<tr>
<td><b>Promo ID</b></td>
<td><b>Promo Name</b></td>
<td><b>Percentage</b></td>
<td><b>Expiration</b></td>
</tr>

<%
    PromotionDAO db = new PromotionDAO();
    List<Promotion> promoList = db.list();
    
    for (int i = 0; i < promoList.size(); i++) {
%>

<tr>  
    <%
        int primaryKey = promoList.get(i).getPromoID();
        String deleteType = "promotion";
    %>
    <td><%=promoList.get(i).getPromoID() %></td>
<td><%=promoList.get(i).getPromoName() %></td>
<td><%=promoList.get(i).getPercentage() %></td>
<td><%=promoList.get(i).getExpiration() %></td>
<td>
    <a href="deleteBook.jsp?deleteid=<%=primaryKey%>&type=<%=deleteType%>">Delete</a>
</td>

</tr>

<% 
}
%>

<!--Add Promotion row:-->
<form name="addPromoButton" method="post" action="promotion">
<tr>
    <td><input type="text" name="new_promoID" onkeyup="checkID(this)" onclick="checkID(this)" onchange="checkID(this)" required></td>
    <td><input type="text" name="new_promoName" onkeyup="checkPromoName(this)" onclick="checkPromoName(this)" onchange="checkPromoName(this)" required></td>
    <td><input type="text" name="new_percentage" onkeyup="checkPerc(this)" onclick="checkPerc(this)" onchange="checkPerc(this)" required></td>
    <td><input type="datetime-local" name="new_expiration" required></td>
    <td><input name="addUpdate" type="submit" value="Add Promotion"></td>
</tr>
</form>

<!--Update promotion Row:-->
<form name="addPromoButton" method="post" action="promotion">
    <tr>
    <td><input type="text" name="new_promoID" onkeyup="checkID(this)" onclick="checkID(this)" onchange="checkID(this)" required></td>
    <td><input type="text" name="new_promoName" onkeyup="checkPromoName(this)" onclick="checkPromoName(this)" onchange="checkPromoName(this)"></td>
    <td><input type="text" name="new_percentage" onkeyup="checkPerc(this)" onclick="checkPerc(this)" onchange="checkPerc(this)"></td>
    <td><input type="datetime-local" name="new_expiration"></td>
    <td><input name="addUpdate" type="submit" value="Update Promotion"></td>
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
<script src="scripts/adminPromoValidation.js"></script>


    </body>
</html>
