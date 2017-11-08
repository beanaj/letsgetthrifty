/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.User;
import entity.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andrewjacobsen
 */
public class Login extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountOrEmail = request.getParameter("accountLogin");
        String password = request.getParameter("accountPassword");
        String userpass = "";
        String type = "";
        User user = null;
        //check simply if it is an email address or not
        int atSymbol = accountOrEmail.indexOf('@');
        //if not present, the type is accountID, else it is an email
        if (atSymbol == -1) {
            type += "a";
        } else {
            type += "e";
        }
        //create info array for validation purposes
        String[] info = {type, accountOrEmail, password};
        LValidation validator = new LValidation();
        Boolean valid = validator.isValid(info);

        if (!valid) {
            String errorLogin = validator.errorMessage();
            request.setAttribute("errorLogin", errorLogin);
            request.getRequestDispatcher("login_register.jsp").forward(request, response);
        } else {
            //now we will nee to fetch the user object
            user = new User(accountOrEmail, type);
            UserDAO dao = new UserDAO();
            //encrypt input passed pw
            password = dao.encrypt(password);
            userpass = user.getPass();
            if (userpass.equals(password)){//correct password, edit session attributes and redirect to the homepage
                HttpSession session = request.getSession();
                session.setAttribute("user", user.getFN());
                session.setAttribute("accountID", user.getAccountID());
                //setting session to expiry in 30 mins
                session.setMaxInactiveInterval(30 * 60);
                Cookie userName = new Cookie("user", user.getAccountID());
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                response.sendRedirect("homepage.jsp");
            } else {//wrong password
                request.setAttribute("errorLogin", "<b>Invalid AccountID/Email or Password</b><br>Please enter a valid accountID/email or password");
                request.getRequestDispatcher("login_register.jsp").forward(request, response);
            }
        }

    }

}
