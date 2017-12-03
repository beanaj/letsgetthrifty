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
import java.util.UUID;
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
public class Reset extends HttpServlet {

    /**
     * Processes requests for HTTP <code>GET</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountID = request.getParameter("accountID");
        String code = request.getParameter("code");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String[] info = {accountID, code};
        //check against accountID and code from database
        CValidation validator = new CValidation();
        Boolean valid = validator.isValid(info);
        if (valid) {
            if (password1.equals(password2)) {
                //change password and redirect to login
                UserDAO db = new UserDAO();
                String newPassE = db.encrypt(password2);
                try {
                    db.updatePassword(accountID, newPassE);
                } catch (SQLException ex) {
                    Logger.getLogger(Reset.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    //reset code so it can't be reused
                    String codeReset = UUID.randomUUID().toString();
                    db.updateUser(accountID, "", "", "", "", "", codeReset, 1);
                } catch (SQLException ex) {
                    Logger.getLogger(ForgotEmail.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                response.sendRedirect("login_register.jsp");
            } else {
                request.setAttribute("error", "<b>Invalid Password</b><br>Please try again");
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "<b>Invalid Reset Code or AccountID</b><br>Please try again");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);

        }
    }

}
