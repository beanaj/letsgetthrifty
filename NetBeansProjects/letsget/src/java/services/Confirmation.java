/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrewjacobsen
 */
public class Confirmation extends HttpServlet {

    /**
     * Processes requests for HTTP <code>GET</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accountID = request.getParameter("accountID");
        String code = request.getParameter("code");
        String[] info = {accountID, code};
        //check against accountID and code from database
        CValidation validator = new CValidation();
        Boolean valid = validator.isValid(info);
        if (valid) {
            activateUser(accountID);
            response.sendRedirect("login_register.jsp");
        } else {
            String error = validator.errorMessage();
            request.setAttribute("error", error);
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);

        }
    }
    
    private void activateUser(String accountID){
        Connection con;
        Statement state;
        DatabaseUtility db = new DatabaseUtility();
        try {
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            state = con.createStatement();
            String sql = "UPDATE users\n" +
                         "SET active = 1\n" +
                         "WHERE userID = '"+accountID+"'";
            state.executeUpdate(sql);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
