/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrewjacobsen
 */
public class CValidation implements IValidator{

    private String error = "";

    public Boolean isValid(String[] information) {
        Boolean valid = true;
        String accountID = information[0];
        String code = information[1];
        String dbAID = "";
        String dbC = "";
        Connection con;
        Statement state;
        DatabaseUtility db = new DatabaseUtility();
        try {
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            state = con.createStatement();
            String sql = "SELECT * FROM users WHERE userID = "+"'"+accountID+"'";

            ResultSet result = state.executeQuery(sql);
            while(result.next()){
                dbC = result.getString("orderConfirmationCode");
            }

        } catch (SQLException exception) {
            error +="<b>Invalid Account ID</b><br>Please try again";
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!dbC.equals(code)){
            valid = false;
            error += "<b>Invalid Confirmation Code or AccountID</b><br>Please try again";
        }
        return valid;
    }

    public String errorMessage() {
        return error;
    }

}
