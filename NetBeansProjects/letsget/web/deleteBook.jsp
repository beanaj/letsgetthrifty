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
    
    
    String type = request.getParameter("type");
    
    switch (type) {
        case "book":
            String item = request.getParameter("deleteid");
            preparedStatement = connection.prepareStatement("delete from books where isbn=" + item);
            preparedStatement.execute();
            response.sendRedirect("adminInventory.jsp");
            break;
        case "agency":
            int itemB = Integer.parseInt(request.getParameter("deleteid"));
            preparedStatement = connection.prepareStatement("delete from shippingagencies where shippingAgencyID=" + itemB);
            preparedStatement.execute();
            response.sendRedirect("adminAgencies.jsp");
            break;
        case "promotion":
            int itemC = Integer.parseInt(request.getParameter("deleteid"));
            preparedStatement = connection.prepareStatement("delete from promotions where promoID=" + itemC);
            preparedStatement.execute();
            response.sendRedirect("adminPromotions.jsp");
            break;
            
           
    }
    
%>
