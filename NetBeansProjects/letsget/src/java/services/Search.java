/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

//import entity.BookDAO;
//import entity.Book;
import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.PreparedStatement;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.RequestDispatcher;
//import java.util.*;
/**
 *
 * @author Ian
 */
public class Search extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //search string
        String search = request.getParameter("input");
        //category of search
        String category = request.getParameter("option");
        //create list of search results
        HttpSession session = request.getSession(false);
        session.setAttribute("input", search);
        session.setAttribute("option", category);
    
        request.getRequestDispatcher("results.jsp").forward(request, response);
        response.sendRedirect("homepage.jsp");
        /*Connection con = null;
        PreparedStatement state = null;
        DatabaseUtility db = new DatabaseUtility();
        
         try {
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            String searchSQL = "SELECT * FROM books WHERE " + category + "=" + search;
            state = con.prepareStatement(searchSQL);
            ResultSet results = state.executeQuery();
           
            response.sendRedirect("search.jsp");
            state.close();
            con.close();
            
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }*/
         
    }

}
