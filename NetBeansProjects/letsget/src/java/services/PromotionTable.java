/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Promotion;
import entity.PromotionDAO;
import entity.SubscriberDAO;
import entity.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Addison
 */
public class PromotionTable extends HttpServlet {
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
            case "Add Promotion":
                int promoID = Integer.parseInt(request.getParameter("new_promoID"));
                String promoName = request.getParameter("new_promoName");
                double perc = Double.parseDouble(request.getParameter("new_percentage"));
                String exp = request.getParameter("new_expiration");
                exp = exp.replace("T", " ");

                Promotion promo = new Promotion(promoID, promoName, perc, exp);
                try {
                    //Add the promotion to the database:
                    promo.addPromo();
                    
                    //Create the promotion email:
                    String subject = "Let's Get Thrifty Promo Code";
                    String message = createConfirmationEmail(promoID, promoName, perc, formatDateTime(exp));
                    
                    //Now send a promotion email to all the subscribers:
                    SubscriberDAO db = new SubscriberDAO();
                    //loop through user emails:
                    List<String> emails = db.listEmails();
                    for (int i = 0; i < emails.size(); i++) {
                        //send the email:
                        try {
                            EmailSender.sendEmail(host, port, user, pass, emails.get(i), subject, message);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    
                } catch (SQLException ex) {
                    String error = "Error: Invalid input, please ensure your ID is unique.";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("adminPromotions.jsp").forward(request, response);
                }
                
                break;
            case "Update Promotion":
                int pID = -1;
                String name = "";
                double per = -1;
                String expi = "";
                 
                
                if (!request.getParameter("new_promoID").isEmpty()) {
                    pID = Integer.parseInt(request.getParameter("new_promoID"));
                } 
                if (!request.getParameter("new_promoName").isEmpty()) {
                    name = request.getParameter("new_promoName");
                } 
                if (!request.getParameter("new_percentage").isEmpty()) {
                    per = Double.parseDouble(request.getParameter("new_percentage"));
                } 
                if (!request.getParameter("new_expiration").isEmpty()) {
                    expi = request.getParameter("new_expiration");
                }
                
                //Update agency in the database:
                PromotionDAO db = new PromotionDAO();
                try {
                    db.updatePromotion(pID, name, per, expi);
                } catch (SQLException ex) {
                    String error = "Error: Invalid input";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("adminPromotions.jsp").forward(request, response);
                }
                
                break;
        } // switch
        response.sendRedirect("adminPromotions.jsp");
        out.close();
    }

    
        private String createConfirmationEmail(int promoID, String promoName, double perc, String exp) {
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
                + " <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "  <tr>\n"
                + "   <td>\n"
                + "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse;\">\n"
                + "    \n"
                + "    <tr>\n"
                + "      <td>\n"
                + "          <b>Use this limited time promo by \n"
                + exp
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "    <tr>\n"
                + "      <td>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "     <tr>\n"
                + "       <td>\n"
                + "       </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Promo Name:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + promoName
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Percent Discount:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + perc
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n" 
                + "    </table>\n"
                + "   </td>\n"
                + "  </tr>\n"
                + " </table>\n"
                + "</body>\n"
                + "</html>";
            return msg;
        }
        
        public String formatDateTime(String date) {
            String dateString = "2011-01-18 00:00:00.0";
            dateString = date;
            LocalDateTime datetime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            String newString = datetime.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, uuuu h:mm a"));
            
            return newString;
        }
}
