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
public class AddressDAO {
    
    public void addAddressUser(Address addr){
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
                    + "a" + addr.getUser().replaceAll( "[^\\d]", "" )+ "\", \""//addressID
                    + addr.getStreet() + "\", \""//street
                    + addr.getCity() + "\", \""//city
                    + addr.getState() + "\", \""//state
                    + addr.getZip() + "\", \""//zip code
                    + addr.getUser() + "\")";//userID
            stmnt.executeUpdate(sql);

        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
