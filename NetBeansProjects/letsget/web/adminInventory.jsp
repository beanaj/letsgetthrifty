<%-- 
    Document   : adminPage
    Created on : Nov 8, 2017, 2:08:17 PM
    Author     : Addison
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin JSP Page</title>
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
                <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="generateTable.jsp" class="pure-menu-link">Inventory</a></li>
                <li class="pure-menu-item"><a href="generateTable.jsp" class="pure-menu-link">Promotions</a></li>

                <li class="pure-menu-item"><a href="#" class="pure-menu-link">Users</a></li>

                <li class="pure-menu-item"><a href="#" class="pure-menu-link">Employees</a></li>
                <li class="pure-menu-item"><a href="#" class="pure-menu-link">Agencies</a></li>
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
<!--<tr bgcolor="#A52A2A">-->
<tr>
<td><b>ISBN</b></td>
<td><b>Genre</b></td>
<td><b>Author</b></td>
<td><b>Title</b></td>
<td><b>Rating</b></td>
<td><b>Publisher</b></td>
<td><b>Publication year</b></td>
<td><b>Quantity</b></td>
<td><b>Buy Price</b></td>
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
    %>
<td><%=resultSet.getString("isbn") %></td>
<td><%=resultSet.getString("genre") %></td>
<td><%=resultSet.getString("author") %></td>
<td><%=resultSet.getString("title") %></td>
<td><%=resultSet.getString("rating") %></td>
<td><%=resultSet.getString("publisher") %></td>
<td><%=resultSet.getInt("publicationYear")%></td>
<td><%=resultSet.getInt("qtyInStock")%></td>
<td><%=resultSet.getInt("buyPrice")%></td>
<td>
    <a href="delete.jsp?deleteid=<%=primaryKey%>">Delete</a>
</td>

</tr>

<% 
}
connection.close();
} catch (Exception e) {
e.printStackTrace();
}
%>

<tr>
    <td><input type="text" id="new_isbn"></td>
    <td><input type="text" id="new_genre"></td>
    <td><input type="text" id="new_author"></td>
    <td><input type="text" id="new_title"></td>
    <td><input type="text" id="new_rating"></td>
    <td><input type="text" id="new_publisher"></td>
    <td><input type="text" id="new_publicationyear"></td>
    <td><input type="text" id="new_quantity"></td>
    <td><input type="text" id="new_buyprice"></td>
    <td><input type="button" onclick="addBook()" value="Add Book"></td>
</tr>

</table>       
<!--END OF TABLE-->
               
<br>
<script>
//    function addBook() {
//        var new_isbn=document.getElementById("new_isbn").value;
//        var new_genre=document.getElementById("new_genre").value;
//        var new_author=document.getElementById("new_author").value; 
//        var new_title=document.getElementById("new_title").value;
//        var new_rating=document.getElementById("new_rating").value;
//        var new_publisher=document.getElementById("new_publisher").value; 
//        var new_publicationyear=document.getElementById("new_publicationyear").value;
//        var new_quantity=document.getElementById("new_quantity").value;
//        var new_buyprice=document.getElementById("new_buyprice").value; 
//        var table = document.getElementById("bookTable");
//        var table_len=(table.rows.length)-1;
//        var row = table.insertRow(table_len).outerHTML="<tr id='row"+table_len+"'>\n\
//                                                            <td id='isbn_row"+table_len+"'>"+new_isbn+"</td>\n\
//                                                            <td id='genre_row"+table_len+"'>"+new_genre+"</td>\n\
//                                                            <td id='author_row"+table_len+"'>"+new_author+"</td>\n\
//\n\                                                         <td id='title_row"+table_len+"'>"+new_title+"</td>\n\
//                                                            <td id='rating_row"+table_len+"'>"+new_rating+"</td>\n\
//                                                            <td id='publisher_row"+table_len+"'>"+new_publisher+"</td>\n\
//\n\                                                         <td id='publicationyear_row"+table_len+"'>"+new_publicationyear+"</td>\n\
//                                                            <td id='quantity_row"+table_len+"'>"+new_quantity+"</td>\n\
//                                                            <td id='buyprice_row"+table_len+"'>"+new_buyprice+"</td>\n\
//\n\                                                         <td><a href="delete.jsp?deleteid='new_isbn'">Delete</a></td>\n\
//                                                        </tr>";
//        document.getElementById("new_isbn").value="";
//        document.getElementById("new_genre").value="";
//        document.getElementById("new_author").value="";
//        document.getElementById("new_title").value="";
//        document.getElementById("new_rating").value="";
//        document.getElementById("new_publisher").value="";
//        document.getElementById("new_publicationyear").value="";
//        document.getElementById("new_quantity").value="";
//        document.getElementById("new_buyprice").value="";
//    }
</script>

            </div>      
        </div>
    </div>
</div>




<script src="scripts/admin.js"></script>


    </body>
</html>
