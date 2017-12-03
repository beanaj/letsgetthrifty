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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andrewjacobsen
 */
public class ForgotEmail extends HttpServlet {

    private String host;
    private String port;
    private String user;
    private String pass;

    public void init() {
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //generate code
        String code = UUID.randomUUID().toString();
        //check to see if email userID combo match, if not return error to same page
        String userID = request.getParameter("accountID");
        String email = request.getParameter("email");
        LValidation validator = new LValidation(); 
        String[] infoE = {"e", email,  ""};
        String[] infoA = {"a", userID,  ""};
        boolean validEmail = validator.isValid(infoE);
        boolean validAccount = validator.isValid(infoA);
        
        if(validEmail && validAccount){
            //update code and send email
            UserDAO db = new UserDAO();
            try {
                db.updateUser(userID, "", "", "", "", "", code, 1);
            } catch (SQLException ex) {
                Logger.getLogger(ForgotEmail.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //send email
            String subject = "Let's Get Thrifty Reset Email";
            String message = createResetEmail(code);
            try {
                String resultMessage = "";
                //send email to registered user
                try {
                    EmailSender.sendEmail(host, port, user, pass, email, subject, message);
                    resultMessage = "The e-mail was sent successfully";
                } catch (Exception ex) {
                    ex.printStackTrace();
                    resultMessage = "There were an error: " + ex.getMessage();
                }
            } finally {
                response.sendRedirect("resetpassword.jsp"); 
            }
            
        }else{
            String errorLogin = validator.errorMessage();
            request.setAttribute("error", "<b>Invalid AccountID or Email</b><br>Please ensure you are entering a valid AccountID and Email");
            request.getRequestDispatcher("forgotpassword.jsp").forward(request, response);
        }
        
        //if so, change code in DB and send email and redirect to confirmation page
        
        
    }
    
    private String createResetEmail(String code) {
        //generate an HTML response email.
        String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + " <head>\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                + "  <title>Registration Confirmation</title>\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n"
                + "</head>\n"
                + "    \n"
                + "<body style=\"margin: 0; padding: 0;\">\n"
                + "Please use the code below to reset your password on the reset page.<br>" 
                + code
                + "</body>\n"
                + "</html>";
        return msg;
    }

}
