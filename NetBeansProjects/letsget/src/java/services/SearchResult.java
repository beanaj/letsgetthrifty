/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 *
 * @author Ian
 */
public class SearchResult extends HttpServlet {


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
       System.out.println("button works");
    }

}