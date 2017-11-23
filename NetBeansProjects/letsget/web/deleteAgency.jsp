<%-- 
    Document   : deleteAgency
    Created on : Nov 23, 2017, 5:30:13 PM
    Author     : Addison
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="services.DatabaseUtility"%>
<%
DatabaseUtility db = new DatabaseUtility();

Connection connection = null;
PreparedStatement preparedStatement = null;
ResultSet rs = null;

try {
Class.forName(db.getDriver());
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

   try{ 
    connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
    
    } catch (Exception e) {
e.printStackTrace();
}
    
    String agencyToDelete = request.getParameter("deleteid");
    
    preparedStatement = connection.prepareStatement("delete from shippingagencies where shippingAgencyID=" + agencyToDelete);
    
    preparedStatement.execute();
    
    response.sendRedirect("adminAgencies.jsp");
%>