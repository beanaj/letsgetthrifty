package services;


import java.io.*;
import java.nio.charset.StandardCharsets;
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
   
   static String convertStreamToString(java.io.InputStream is) {
    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
    return s.hasNext() ? s.next() : "";
    }
   

   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
 
      // Set the response MIME type of the response message
      response.setContentType("text/html");
      // Allocate a output writer to write the response message into the network socket
      String name = request.getParameter("name");
      String email = request.getParameter("email");
      String password1 = request.getParameter("password1");
      String code = UUID.randomUUID().toString();
      
      String subject = "Registration Confirmation";
      PrintWriter out = response.getWriter();
      InputStream registrationPage  = getClass().getResourceAsStream("/services/resources/regresponse.html");
      String message = convertStreamToString(registrationPage);
      message += "Name: " + name;
      message += "<br>Email: " + email;
      message += "<br><br>Confirmation Code: " + code;
      //generate confirmation code
      
              
       // Write the response message, in an HTML page
      try {
         out.println("<html>");
         out.println("<head><title>Success!!!</title></head>");
         out.println("<body>");
         out.println("<h1>You have been registered!</h1>");
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

         out.println("<p>Please check your messages at: " + email + "</p>");
         out.println("</body></html>");
      } finally {
         out.close();  // Always close the output writer
      }
   }
}