<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>

<!--Addison-->
<%
    String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://letsgetthrifty-database.cgg5mwmrnbyi.us-east-1.rds.amazonaws.com:3306/letsget?zeroDateTimeBehavior=convertToNull";
String dbName = "letsget";
String userId = "admin";
String password = "password";

Connection connection = null;
PreparedStatement preparedStatement = null;
ResultSet rs = null;

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

   try{ 
    connection = DriverManager.getConnection(connectionUrl, userId, password);
    
    } catch (Exception e) {
e.printStackTrace();
}
    
    
%>