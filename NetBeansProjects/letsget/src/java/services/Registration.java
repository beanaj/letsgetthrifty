// NOTE, MAKE SURE TO ADD FEATURE TO PREVENT DOUBLE REGISTRATION


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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Registration extends HttpServlet {

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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        //gather all registration information
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
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
        String password2 = request.getParameter("password2");
        String userType = "u";
        //generate confirmation code
        String code = UUID.randomUUID().toString();
        //get registration date for generating unique account id
        //Generate unique account ID using registration date and first initials
        String accountID = "";
        //note:: Might be worthwhile to check to see if the id is used, although very low chance of this.
        
        // Set the response MIME type of the response message
        response.setContentType("text/html");
        // Allocate a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();
        //Generate response email
        //add user to database
        String[] information = {firstName, lastName, phone, email, userType, password1, password2};
        
        //validate the information input
        RValidation validator = new RValidation();
        Boolean valid = validator.isValid(information);
        String error;
        if (valid == false) {
            error = validator.errorMessage();
            request.setAttribute("error", error);
            request.getRequestDispatcher("login_register.jsp").forward(request, response);
        } else {
            //fetch and generate an accountID
            //first we need to grab the hash from the last entry
            int hash = getHashCounter();
            //now with our most recent hash
            accountID = generateAccountNumber(hash, lastName);
            // Write the response message, in an HTML page
            //Add the user to the database
            hash++;
            String payment = accountID.substring(2);
            addUser(information, accountID, code, payment, hash);
            addPayment(accountID, payment, card, exp, type);
            addAddress(accountID, street, city, zip, state);
            String name = firstName + " " + lastName;
            String subject = "Let's Get Thrifty Registration Confirmation";
            String message = createConfirmationEmail(name, email, phone, street, city, zip, state, card, exp, type, code, accountID);
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
                //this will need to be changed to a non absolute address
                response.sendRedirect("confirmation.jsp");
                out.close();  // Always close the output writer
            }
        }
    }
    
    private void addAddress(String accountID, String street, String city, String zip, String state){
        //insert user address information into database
        Connection con = null;
        Statement stmnt = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            stmnt = con.createStatement();
            String sql = "INSERT INTO addresses (addressID, street, city, state, zip, userID) VALUES (\""
                    + "a" + accountID.replaceAll( "[^\\d]", "" )+ "\", \""//addressID
                    + street + "\", \""//street
                    + city + "\", \""//city
                    + state + "\", \""//state
                    + zip + "\", \""//zip code
                    + accountID + "\")";//userID
            stmnt.executeUpdate(sql);

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addPayment(String accountID, String payment, String number, String expiration, String type){
        //insert user payment information into databse
        Connection con = null;
        Statement state = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            //remove anything but numbers from the payment
            payment = payment.replaceAll( "[^\\d]", "" ); 
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            state = con.createStatement();
            expiration = expiration.substring(0,2)+"/01"+expiration.substring(2);
            String sql = "INSERT INTO creditcards (creditCardID, userID, creditCardNumber, creditCardType, expirationDate) VALUES (\""
                    + "999"+payment+ "\", \""//creditCardID
                    + accountID + "\", \""//userID
                    + number + "\", \""//creditcardnumber
                    + type + "\", \""//creditcardtype
                    + expiration + "\")";//expiration
            state.executeUpdate(sql);

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addUser(String[] info, String accountID, String code, String pay, int hash){
        //insert user information into database
        //0-firstName, 1-lastName, 2-phone, 3-email, 4-userType, 5-password1, 6-password2
        Connection con = null;
        Statement state = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            state = con.createStatement();
            String sql = "INSERT INTO users (userID, firstName, lastName, phone, email, paymentInfo, userType, userPassword, hash, orderConfirmationCode, active) VALUES (\""
                    + accountID + "\", \""//userID
                    + info[0] + "\", \""//firstName
                    + info[1] + "\", \""//lastName
                    + info[2] + "\", \""//phone
                    + info[3] + "\", \""//email
                    + pay + "\", \""//paymentInfo
                    + info[4] + "\", \""//usertyp
                    + info[5] + "\", \""//userpass
                    + hash + "\", \""//hash
                    + code + "\", \""//confirmationcode
                    + 0 + "\")";//active
            state.executeUpdate(sql);

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private String generateAccountNumber(int hash, String lastName){
        hash = hash*534;
        hash = hash/4;
        hash = hash+3;
        String accountID = lastName.substring(0,2) + hash;
        return accountID;
    }
    
    
    private int getHashCounter(){
        int hash = -1;
        DatabaseUtility db = new DatabaseUtility();
        Connection con = null;
        Statement state = null;
        try{
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            state = con.createStatement();
            String sql = "SELECT * FROM users ORDER BY hash DESC LIMIT 1";
            ResultSet result = state.executeQuery(sql);
            while(result.next()){
                hash = result.getInt("hash");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }

    private String createConfirmationEmail(String name, String email, String phone, String street, String city,
            String zip, String state, String card, String exp, String type, String code, String accountID) {
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
                + "          <b>Welcome to Let's Get Thrifty! Below are your account details. <br>These can be changed via the MyAccount page upon login.</b><br><br>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "    <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Name:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + name
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "    <tr>\n"
                + "      <td>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Phone:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + phone
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
                + "           Street:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + street
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           City:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + city
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
                + "           State:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + state
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "       <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Card:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + card
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Expiration:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + exp
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "     <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + "           Type:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + type
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
                + "           accountID:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\">\n"
                + accountID
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "        <tr>\n"
                + "      <td>\n"
                + "       \n"
                + "      </td>\n"
                + "     </tr>\n"
                + "     <tr>\n"
                + "        <tr>\n"
                + "      <td>\n"
                + "       <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "         <tr>\n"
                + "          <td width=\"260\" valign=\"top\"><br>\n"
                + "           Confirmation Code:\n"
                + "          </td>\n"
                + "          <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\n"
                + "           &nbsp;\n"
                + "          </td>\n"
                + "          <td width=\"260\" valign=\"top\"><br>\n"
                + code
                + "          </td>\n"
                + "         </tr>\n"
                + "        </table>\n"
                + "      </td>\n"
                + "     </tr>\n"
                + "    </table>\n"
                + "   </td>\n"
                + "  </tr>\n"
                + " </table>\n"
                + "</body>\n"
                + "</html>";
        return msg;
    }
}
