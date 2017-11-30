/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.DatabaseUtility;
import services.Registration;

/**
 *
 * @author andrewjacobsen
 */
public class PaymentDAO {

    public Payment getPaymentByID(String payID) {
        Payment pay = new Payment();
        Connection con = null;
        Statement state = null;
        ResultSet result = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            state = con.createStatement();
            String sql = "SELECT * FROM creditcards WHERE creditCardID = '" + payID + "'";

            result = state.executeQuery(sql);
            while (result.next()) {
                pay.creditCardID = result.getString("creditCardID");
                pay.userID = result.getString("userID");
                pay.creditCardNumber = result.getString("creditCardNumber");
                pay.creditCardType = result.getString("creditCardType");
                pay.expirationDate = result.getString("expirationDate");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pay;
    }

    public void addPayment(Payment pay) {
        //insert user payment information into databse
        Connection con = null;
        Statement state = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            String expiration = pay.getExp();
            //remove anything but numbers from the payment

            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            state = con.createStatement();
            expiration = expiration.substring(0, 2) + "/01" + expiration.substring(2);
            String sql = "INSERT INTO creditcards (creditCardID, userID, creditCardNumber, creditCardType, expirationDate) VALUES (\""
                    + pay.getCCID() + "\", \""//creditCardID
                    + pay.getUserID() + "\", \""//userID
                    + pay.getNum() + "\", \""//creditcardnumber
                    + pay.getType() + "\", \""//creditcardtype
                    + expiration + "\")";//expiration
            state.executeUpdate(sql);
            con.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePayment(String id, String num, String exp, String type) {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            
            if (!num.isEmpty()) {
                String query = "UPDATE creditcards SET creditCardNumber = ? WHERE creditCardID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, num);
                stat.setString(2, id);
                stat.executeUpdate();
            }
            if (!exp.isEmpty()) {
                String query = "UPDATE creditcards SET expirationDate = ? WHERE creditCardID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, exp);
                stat.setString(2, id);
                stat.executeUpdate();
            }
            System.out.println(type);
            if (type!=null&&!type.isEmpty()) {
                String query = "UPDATE creditcards SET creditCardType = ? WHERE creditCardID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, type);
                stat.setString(2, id);
                stat.executeUpdate();
            }
           
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
