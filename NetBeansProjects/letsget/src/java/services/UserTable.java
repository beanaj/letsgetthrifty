/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Addison
 */
public class UserTable extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

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
        
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String hiddenParam = request.getParameter("addUpdate");
      
        switch (hiddenParam) {
            case "Update User":
                String id = "";
                String fN = "";
                String lN = "";
                String phone = ""; 
                String email = "";
                String type = "";
                String code = "";
                int active = -1; 
                
                if (!request.getParameter("new_userID").isEmpty()) {
                    id = request.getParameter("new_userID");
                } 
                if (!request.getParameter("new_firstName").isEmpty()) {
                    fN = request.getParameter("new_firstName");
                } 
                if (!request.getParameter("new_lastName").isEmpty()) {
                    lN = request.getParameter("new_lastName");
                } 
                if (!request.getParameter("new_phone").isEmpty()) {
                    phone = request.getParameter("new_phone");
                } 
                if (!request.getParameter("new_email").isEmpty()) {
                    email = request.getParameter("new_email");
                } 
                if (!request.getParameter("new_userType").isEmpty()) {
                    type = request.getParameter("new_userType");
                } 
                if (!request.getParameter("new_orderConfirmationCode").isEmpty()) {
                    code = request.getParameter("new_orderConfirmationCode");
                } 
                if (!request.getParameter("new_active").isEmpty()) {
                    active = Integer.parseInt(request.getParameter("new_active"));
                } 
                
                
                //Update agency in the database:
                UserDAO db = new UserDAO();
                try {
                    db.updateUser(id, fN, lN, phone, email, type, code, active);
                } catch (SQLException ex) {
                    String error = "Error: Invalid input";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("adminAgencies.jsp").forward(request, response);
                }
                
                break;
                
            case "Promote User":
                String idB = "";
                String fNB = "";
                String lNB = "";
                String phoneB = ""; 
                String emailB = "";
                String typeB = "e";
                String codeB = "";
                int activeB = -1; 
                
                if (!request.getParameter("new_userID").isEmpty()) {
                    idB = request.getParameter("new_userID");
                } 
                
                UserDAO dbB = new UserDAO();
                try {
                    dbB.updateUser(idB, fNB, lNB, phoneB, emailB, typeB, codeB, activeB);
                } catch (SQLException ex) {
                    String error = "Error: Invalid input";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("adminAgencies.jsp").forward(request, response);
                }
                break;
            case "Suspend User":
                String idC = "";
                String fNC = "";
                String lNC = "";
                String phoneC = ""; 
                String emailC = "";
                String typeC = "";
                String codeC = "";
                int activeC = 0; 
                
                if (!request.getParameter("new_userID").isEmpty()) {
                    idC = request.getParameter("new_userID");
                } 
                
                UserDAO dbC = new UserDAO();
                try {
                    dbC.updateUser(idC, fNC, lNC, phoneC, emailC, typeC, codeC, activeC);
                } catch (SQLException ex) {
                    String error = "Error: Invalid input";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("adminAgencies.jsp").forward(request, response);
                }
                break;
                
        } // switch
        
        String tableType = request.getParameter("tableType");
        
        switch (tableType) {
            case "user":
                response.sendRedirect("adminUsers.jsp");
                break;
            case "employee":
                response.sendRedirect("adminEmployees.jsp");
        }
        out.close();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
