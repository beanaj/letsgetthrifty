<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
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
    
    String bookToDelete = request.getParameter("deleteid");
    
    preparedStatement = connection.prepareStatement("delete from books where isbn=" + bookToDelete);
    
    preparedStatement.execute();
    
    response.sendRedirect("adminInventory.jsp");
%>
