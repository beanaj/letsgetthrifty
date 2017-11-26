/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Connection;
import java.sql.DriverManager;
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
    
    public Payment getPaymentByID(String payID){
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
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pay;
    }
    
    public void addPayment(Payment pay){
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
            expiration = expiration.substring(0,2)+"/01"+expiration.substring(2);
            String sql = "INSERT INTO creditcards (creditCardID, userID, creditCardNumber, creditCardType, expirationDate) VALUES (\""
                    + pay.getCCID()+ "\", \""//creditCardID
                    + pay.getUserID() + "\", \""//userID
                    + pay.getNum() + "\", \""//creditcardnumber
                    + pay.getType() + "\", \""//creditcardtype
                    + expiration + "\")";//expiration
            state.executeUpdate(sql);

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
