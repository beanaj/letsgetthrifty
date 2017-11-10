<%-- 
    Document   : homepage
    Created on : Nov 5, 2017, 12:07:53 PM
    Author     : Ian
--%>
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
      <%
        //allow access only if session exists
        String user = null;
        if(session.getAttribute("user") == null){
               System.out.println("FAIL");
        }else user = (String) session.getAttribute("user");
        String userName = null;
        String sessionID = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
        for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) userName = cookie.getValue();
                if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
        }
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
        <div class="pure-u-1-5"> 
        <div id ="menu" class="pure-menu">
            <a class="pure-menu-heading" href="#">Welcome!</a>

            <ul class="pure-menu-list">
                <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="generateTable.jsp" class="pure-menu-link">Home</a></li>
                <li class="pure-menu-item"><a href="generateTable.jsp" class="pure-menu-link">My Account</a></li>

                <li class="pure-menu-item"><a href="#" class="pure-menu-link">Search</a></li>

                <li class="pure-menu-item"><a href="#" class="pure-menu-link">Log In/Register</a></li>
                <li class="pure-menu-item"><a href="#" class="pure-menu-link">Sign Out</a></li>
            </ul>
        </div>
        </div>
        <div class="pure-u-4-5">
            <div class="search"
                <form class="pure-form">
                    <input class="searchbar" type="search" placeholder="Search by Title">
                    <button type="submit" class="pure-button pure-button-primary">Search!</button>
                </form>
            </div>
                <h2>RECENT ADDITIONS</h2>
  
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
<!--<h2 align="center"><font><strong>Retrieve data from database in jsp</strong></font></h2>-->
<table id="bookTable" align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>

<%
try{ 
connection = DriverManager.getConnection(connectionUrl, userId, password);
statement=connection.createStatement();
String sql ="SELECT * FROM books";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<!--<tr bgcolor="#DEB887">-->
<tr>  
    <%
        String primaryKey = resultSet.getString("isbn");
        String pic = resultSet.getString("picture");
    %>
<td><image src ="<%=pic%>"></td>
<td><%=resultSet.getInt("buyPrice")%></td>    
<td><%=resultSet.getString("isbn") %></td>
<td><%=resultSet.getString("genre") %></td>
<td><%=resultSet.getString("author") %></td>
<td><%=resultSet.getString("title") %></td>
<td><%=resultSet.getString("rating") %></td>
<td><%=resultSet.getString("publisher") %></td>
<td><%=resultSet.getInt("publicationYear")%></td>
<td><%=resultSet.getInt("qtyInStock")%></td>
<td><%=resultSet.getInt("buyPrice")%></td>
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
    <script src="scripts/homepage.js"></script>
  </body>
</html>
