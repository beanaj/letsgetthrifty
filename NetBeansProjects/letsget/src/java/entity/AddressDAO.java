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
public class AddressDAO {

    public void addAddressUser(Address addr) {
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
                    + "a" + addr.getUser().replaceAll("[^\\d]", "") + "\", \""//addressID
                    + addr.getStreet() + "\", \""//street
                    + addr.getCity() + "\", \""//city
                    + addr.getState() + "\", \""//state
                    + addr.getZip() + "\", \""//zip code
                    + addr.getUser() + "\")";//userID
            stmnt.executeUpdate(sql);
            con.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addAddress(Address addr) {
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
                    + addr.getAddressID()+ "\", \""//addressID
                    + addr.getStreet() + "\", \""//street
                    + addr.getCity() + "\", \""//city
                    + addr.getState() + "\", \""//state
                    + addr.getZip() + "\", \""//zip code
                    + addr.getUser() + "\")";//userID
            stmnt.executeUpdate(sql);
            con.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Address getAddressByID(String addressID, String userID) {
        Address returnA = new Address();
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
            String sql = "SELECT * FROM addresses WHERE userID = '" + userID + "' AND addressID = '" + addressID + "'";
            result = state.executeQuery(sql);
            while (result.next()) {
                returnA.addressID = addressID;
                returnA.street = result.getString("street");
                returnA.city = result.getString("city");
                returnA.state = result.getString("state");
                returnA.zip = result.getString("zip");
                returnA.userID = result.getString("userID");
                returnA.shippingAgencyID = result.getString("shippingAgencyID");
                returnA.supplierID = result.getString("supplierID");
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnA;
    }
    
    public void updateAddress(String id, String street, String city, String state, String zip) {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            
            if (!street.isEmpty()) {
                String query = "UPDATE addresses SET street = ? WHERE addressID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, street);
                stat.setString(2, id);
                stat.executeUpdate();
            }
            if (!city.isEmpty()) {
                String query = "UPDATE addresses SET city = ? WHERE addressID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, city);
                stat.setString(2, id);
                stat.executeUpdate();
            }
            if (!state.isEmpty()) {
                String query = "UPDATE addresses SET state = ? WHERE addressID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, state);
                stat.setString(2, id);
                stat.executeUpdate();
            }
            if (!zip.isEmpty()) {
                String query = "UPDATE addresses SET zip = ? WHERE addressID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, zip);
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
