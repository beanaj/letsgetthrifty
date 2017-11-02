package services;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;



public class Registration extends HttpServlet {
   
    private String host;
    private String port;
    private String user;
    private String pass;


   public void init(){
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
   
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
 
      //gather all registration information
      String name = request.getParameter("name");
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      String street = request.getParameter("street");
      String city = request.getParameter("city");
      String zip = request.getParameter("zip");
      String state = request.getParameter("state");
      String card = request.getParameter("card");
      String exp = request.getParameter("exp");
      String type = request.getParameter("type");
      String password1 = request.getParameter("password1");
      String code = UUID.randomUUID().toString();
      //get registration date for generating unique account id
      //change how id is generated
      DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
      Date date = new Date();
      String regDate= dateFormat.format(date);
      //Generate unique account ID using registration date and first initials
      String accountID= name.substring(0, 3) + regDate;
      //note:: Might be worthwhile to check to see if the id is used, although very low chance of this.
      
      // Set the response MIME type of the response message
      response.setContentType("text/html");
      // Allocate a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();
      //Generate response email
      String subject = "Welcome! Registration Confirmation";
      String message = createConfirmationEmail(name, email, phone, street, city, zip, state, card, exp, type, code, accountID);
      //generate confirmation code
      
              
       // Write the response message, in an HTML page
      try {
         String resultMessage = "";
         //send email to registered user
         try {
            EmailSender.sendEmail(host, port, user, pass, email, subject,
                  message);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        }  
      } finally {
          //this will need to be changed to a non absolute address
         response.sendRedirect("http://localhost:8080/letsget/index.html");
         out.close();  // Always close the output writer
      }
      
   }
   
   static String convertStreamToString(java.io.InputStream is) {
    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
    return s.hasNext() ? s.next() : "";
    }
   
   private String createConfirmationEmail(String name, String email, String phone, String street, String city,
           String zip, String state, String card, String exp, String type, String code, String accountID){
           //generate an HTML response email.
           String msg = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            " <head>\n" +
            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
            "  <title>Registration Confirmation</title>\n" +
            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
            "</head>\n" +
            "    \n" +
            "<body style=\"margin: 0; padding: 0;\">\n" +
            " <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "  <tr>\n" +
            "   <td>\n" +
            "    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse;\">\n" +
            "    \n" +
            "    <tr>\n" +
            "      <td>\n" +
            "          <b>Welcome to Let's Get Thrifty, below are your confirmation details.</b><br><br>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "     <tr>\n" +
            "    <tr>\n" +
            "      <td>\n" +
            "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "         <tr>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            "           Name:\n" +
            "          </td>\n" +
            "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
            "           &nbsp;\n" +
            "          </td>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            name +
            "          </td>\n" +
            "         </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "    <tr>\n" +
            "      <td>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "     <tr>\n" +
            "     <tr>\n" +
            "      <td>\n" +
            "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "         <tr>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            "           Phone:\n" +
            "          </td>\n" +
            "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
            "           &nbsp;\n" +
            "          </td>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            phone +
            "          </td>\n" +
            "         </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "     <tr>\n" +
            "      <td>\n" +
            "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "         <tr>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            "           Street:\n" +
            "          </td>\n" +
            "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
            "           &nbsp;\n" +
            "          </td>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            street+
            "          </td>\n" +
            "         </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "     <tr>\n" +
            "     <tr>\n" +
            "      <td>\n" +
            "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "         <tr>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            "           City:\n" +
            "          </td>\n" +
            "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
            "           &nbsp;\n" +
            "          </td>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            city+
            "          </td>\n" +
            "         </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "     <tr>\n" +
            "      <td>\n" +
            "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "         <tr>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            "           State:\n" +
            "          </td>\n" +
            "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
            "           &nbsp;\n" +
            "          </td>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            state+
            "          </td>\n" +
            "         </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "       <tr>\n" +
            "      <td>\n" +
            "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "         <tr>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            "           Card:\n" +
            "          </td>\n" +
            "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
            "           &nbsp;\n" +
            "          </td>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            card+
            "          </td>\n" +
            "         </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "     <tr>\n" +
            "      <td>\n" +
            "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "         <tr>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            "           Expiration:\n" +
            "          </td>\n" +
            "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
            "           &nbsp;\n" +
            "          </td>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            exp+
            "          </td>\n" +
            "         </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "     <tr>\n" +
            "     <tr>\n" +
            "      <td>\n" +
            "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "         <tr>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            "           Type:\n" +
            "          </td>\n" +
            "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
            "           &nbsp;\n" +
            "          </td>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            type+
            "          </td>\n" +
            "         </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "     <tr>\n" +
            "      <td>\n" +
            "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "         <tr>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            "           accountID:\n" +
            "          </td>\n" +
            "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
            "           &nbsp;\n" +
            "          </td>\n" +
            "          <td width=\"260\" valign=\"top\">\n" +
            accountID+
            "          </td>\n" +
            "         </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "        <tr>\n" +
            "      <td>\n" +
            "       \n" +
            "      </td>\n" +
            "     </tr>\n" +
            "     <tr>\n" +
            "        <tr>\n" +
            "      <td>\n" +
            "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
            "         <tr>\n" +
            "          <td width=\"260\" valign=\"top\"><br>\n" +
            "           Confirmation Code:\n" +
            "          </td>\n" +
            "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n" +
            "           &nbsp;\n" +
            "          </td>\n" +
            "          <td width=\"260\" valign=\"top\"><br>\n" +
            code+
            "          </td>\n" +
            "         </tr>\n" +
            "        </table>\n" +
            "      </td>\n" +
            "     </tr>\n" +
            "    </table>\n" +
            "   </td>\n" +
            "  </tr>\n" +
            " </table>\n" +
            "</body>\n" +
            "</html>";
       return msg;
   }
}