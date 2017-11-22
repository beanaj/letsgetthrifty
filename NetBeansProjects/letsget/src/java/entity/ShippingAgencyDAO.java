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
                agency.setContactName(rs.getString("contactPhone"));
                agencies.add(agency);
            }
        
        }
        return agencies;
    }
    
    //Add a book to the database
    public void addAgency(ShippingAgency a) {
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
