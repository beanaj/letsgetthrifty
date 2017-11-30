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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.DatabaseUtility;

/**
 *
 * @author Addison
 */
public class ShippingAgencyDAO {
    public List<ShippingAgency> list() throws SQLException {
        DatabaseUtility db = new DatabaseUtility();
        
        List<ShippingAgency> agencies = new ArrayList<ShippingAgency>();
        
        try {
            Class.forName(db.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try (
            Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM shippingagencies");
            ResultSet rs = statement.executeQuery();
                ) {
            while (rs.next()) {
                ShippingAgency agency = new ShippingAgency();
                agency.setAgencyID(rs.getInt("shippingAgencyID"));
                agency.setAgencyName(rs.getString("agencyName"));
                agency.setPhone(rs.getString("phone"));
                agency.setContactName(rs.getString("contactName"));
                agency.setContactPhone(rs.getString("contactPhone"));
                agencies.add(agency);
            }
            connection.close();
        
        }
        return agencies;
    }
    
    //Add a book to the database
    public void addAgency(ShippingAgency a) throws SQLException {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            String query = "INSERT INTO shippingagencies (shippingAgencyID, agencyName, phone, contactName, contactPhone) VALUES (?, ?, ?, ?, ?)";
            stat = conn.prepareStatement(query);
            stat.setInt(1, a.getAgencyID());
            stat.setString(2, a.getAgencyName());
            stat.setString(3, a.getPhone());
            stat.setString(4, a.getContactName());
            stat.setString(5, a.getContactPhone());
            stat.execute();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    //get agency from the database
 
    public void updateAgency(int saID, String aName, String phone, String cName, String cPhone) throws SQLException {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            
            if (!aName.isEmpty()) {
                String query = "UPDATE shippingagencies SET agencyName = ? WHERE shippingAgencyID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, aName);
                stat.setInt(2, saID);
                stat.executeUpdate();
            }
            if (!phone.isEmpty()) {
                String query = "UPDATE shippingagencies SET phone = ? WHERE shippingAgencyID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, phone);
                stat.setInt(2, saID);
                stat.executeUpdate();
            }
            if (!cName.isEmpty()) {
                String query = "UPDATE shippingagencies SET contactName = ? WHERE shippingAgencyID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, cName);
                stat.setInt(2, saID);
                stat.executeUpdate();
            }
            if (!cPhone.isEmpty()) {
                String query = "UPDATE shippingagencies SET contactPhone = ? WHERE shippingAgencyID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, cPhone);
                stat.setInt(2, saID);
                stat.executeUpdate();
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    ShippingAgency getShippingAgency(String id) {
        ShippingAgency agency = new ShippingAgency();
        DatabaseUtility db = new DatabaseUtility();
        try (
            Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM shippingagencies WHERE shippingAgencyID = '"+id+"'");
            ResultSet rs = statement.executeQuery();
                ) {
            while (rs.next()) {
                agency.setAgencyID(rs.getInt("shippingAgencyID"));
                agency.setAgencyName(rs.getString("agencyName"));
                agency.setPhone(rs.getString("phone"));
                agency.setContactName(rs.getString("contactName"));
                agency.setContactPhone(rs.getString("contactPhone"));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ShippingAgencyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agency;
    }
}
