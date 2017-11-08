/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Connection;
import java.sql.DriverManager;
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
public class UserDAO {
    private DatabaseUtility db;
    
    //add user to database
    public void addUser(User user){
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
                    + user.getAccountID() + "\", \""//userID
                    + user.getFN() + "\", \""//firstName
                    + user.getLN() + "\", \""//lastName
                    + user.getPhone() + "\", \""//phone
                    + user.getEmail() + "\", \""//email
                    + user.getPaymentInfo() + "\", \""//paymentInfo
                    + user.getType() + "\", \""//usertyp
                    + user.getPass() + "\", \""//userpass
                    + user.getHash() + "\", \""//hash
                    + user.getCode() + "\", \""//confirmationcode
                    + 0 + "\")";//active
            state.executeUpdate(sql);

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //get user info from database
    public User getUser(String accountID){
        User butt = new User(accountID);//need to fetch all info from database, make a user, set active and return it
        return butt;
    }
    
}
