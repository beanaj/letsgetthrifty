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
        <title>Admin Employees</title>
        <link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/adminstyle.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">      
    </head>


    <body>
        <%
            if (session.getAttribute("type").equals("u")) {
                response.sendRedirect("homepage.jsp");
            } else if (session.getAttribute("type").equals("e")) {
                response.sendRedirect("shipmentEmployee.jsp");
            } else if (session.getAttribute("type").equals("m")) {
                response.sendRedirect("managerReports.jsp");
            } else if (session.getAttribute("type")==null) {
                response.sendRedirect("homepage.jsp");
            }
        %>
        <div class="pure-g">


            <!-- Menu toggle -->
            <a href="#menu" id="menuLink" class="menu-link">
                <!-- Hamburger icon -->
                <span></span>
            </a>

            <div class="pure-u-1-4">
                <div id="menu">
                    <div class="pure-menu">
                        <a class="pure-menu-heading" href="#">Admin</a>

                        <ul class="pure-menu-list">
                            <li class="pure-menu-item"><a href="signout" class="pure-menu-link">Sign Out</a></li>
                            <li class="pure-menu-item"><a href="adminInventory.jsp" class="pure-menu-link">Inventory</a></li>
                            <li class="pure-menu-item"><a href="adminPromotions.jsp" class="pure-menu-link">Promotions</a></li>
                            <li class="pure-menu-item"><a href="adminUsers.jsp" class="pure-menu-link">Users</a></li>
                            <li class="pure-menu-item menu-item-divided pure-menu-selected"><a href="adminEmployees.jsp" class="pure-menu-link">Employees</a></li>
                            <li class="pure-menu-item"><a href="adminAgencies.jsp" class="pure-menu-link">Agencies</a></li>
                            <li class="pure-menu-item"><a href="adminReports.jsp" class="pure-menu-link">Reports</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="pure-u-5-8">
                <div class="banner">
                    <h1 class="bannerhead">
                        <img src="img/letsgetlogo.png" alt="logo" height = "150" width="350">  
                    </h1>
                </div>
                <div class="browseheader">
                    EMPLOYEES
                </div>              

                <!--THE TABLE:::-->
                <div class="tableholder">
                    <table>
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
                                                <!--Update User Row:-->
                        <form name="updateUserButton" method="post" action="userTable">
                            <tr>
                                <td><input type="text" name="new_userID" onkeyup="checkID(this)" onclick="checkID(this)" onchange="checkID(this)" required></td>
                                <td><input type="text" name="new_firstName" onkeyup="checkName(this)" onclick="checkName(this)" onchange="checkName(this)"></td>
                                <td><input type="text" name="new_lastName" onkeyup="checkName(this)" onclick="checkName(this)" onchange="checkName(this)"></td>
                                <td><input type="text" name="new_phone" onkeyup="checkPhone(this)" onclick="checkPhone(this)" onchange="checkPhone(this)"></td>
                                <td><input type="text" name="new_email" onkeyup= "checkEmail(this)" onclick="checkEmail(this)" onchange="checkEmail(this)"></td>
                                <td><input type="text" name="new_userType" onkeyup= "checkType(this)" onclick="checkType(this)" onchange="checkType(this)"></td>
                                <td><input type="text" name="new_orderConfirmationCode" onkeyup="checkCode(this)" onclick="checkCode(this)" onchange="checkCode(this)"></td>
                                <td><input type="text" name="new_active" onkeyup="checkActive(this)" onclick="checkActive(this)" onchange="checkActive(this)"></td>
                            <input type="hidden" name = "tableType" value="employee">
                            <td><input name="addUpdate" type="submit" value="Update User" class="button"></td>
                            </tr>
                        </form>
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
                                String type = userList.get(i).getType();
                                //Only display the users that are employees:
                                if (type.equals("e") || type.equals("m")) {
                            %>
                            <td><%=userList.get(i).getAccountID()%></td>
                            <td><%=userList.get(i).getFN()%></td>
                            <td><%=userList.get(i).getLN()%></td>
                            <td><%=userList.get(i).getPhone()%></td>
                            <td><%=userList.get(i).getEmail()%></td>
                            <td><%=userList.get(i).getType()%></td>
                            <td><%=userList.get(i).getCode()%></td>
                            <td><%=userList.get(i).getActive()%></td>

                        </tr>

                        <%
                                }
                            }
                        %>



                    </table>     
                </div>
                <!--END OF TABLE-->
            </div>
            <div id="error">
                ${requestScope.error}
            </div>       
            <div class="pure-u-1-8"></div>

        </div>

        <script src="scripts/admin.js"></script>
        <script src="scripts/adminEmployeeVerification.js"></script>


    </body>
</html>
