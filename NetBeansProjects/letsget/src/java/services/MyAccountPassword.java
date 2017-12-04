/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.User;
import entity.UserDAO;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author codar_000
 */
public class MyAccountPassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        
        String hiddenParam = request.getParameter("updatePassword");
        
        String id = "";
        String oldPass = "";
        String newPass = "";
        String newPassV = "";

        id = request.getParameter("userID");
        oldPass = request.getParameter("oldPass");
        newPass = request.getParameter("newPass");
        newPassV = request.getParameter("newPassV");
        
        UserDAO db = new UserDAO();
        User user = new User(id, "u");
        if(newPass != null && newPassV != null && oldPass != null){    
            if(newPass.equals(newPassV)){
                if (db.encrypt(oldPass).equals(user.getPass())) {
                    try {
                        String newPassE = db.encrypt(newPass);
                        db.updatePassword(id, newPassE);
                        //System.out.println("It Worked!");
                    } catch (SQLException ex) {
                        String error = "Error: Invalid input";
                        request.setAttribute("error", error);
                        request.getRequestDispatcher("my_accountPassword.jsp").forward(request, response);
                    }
                }
                else{
                    System.out.println("Old Password Incorrect");
                }
            }
            else{
                System.out.println("Passwords dont Match");
            }
        }
        else{
            System.out.println("One or More Fields Left Blank");
        }
        response.sendRedirect("my_accountPassword.jsp");    
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
