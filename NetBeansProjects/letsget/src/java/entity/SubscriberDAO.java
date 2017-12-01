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
public class SubscriberDAO {

    public List<String> listEmails() throws SQLException {
        DatabaseUtility db = new DatabaseUtility();
        
        List<String> emails = new ArrayList<String>();
        
        try {
            Class.forName(db.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try (
            Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM subscribers");
            ResultSet rs = statement.executeQuery();
                ) {
            while (rs.next()) {
                //Get their email and add it to the emails List
                String email = rs.getString("email");
                emails.add(email);  
            }
            connection.close();
        
        }
        return emails;
    }
    public void addUser(String subscribeID, String email) throws SQLException {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            
                String query = "INSERT INTO subscribers (subscribeID, email) VALUES (?, ?)";
                stat = conn.prepareStatement(query);
                stat.setString(1, subscribeID);
                stat.setString(2, email);
                stat.executeUpdate();
            
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void removeUser(String id, String email) throws SQLException {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            System.out.println("IM IN");
                String query = "DELETE FROM subscribers WHERE subscribeID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, id);
                stat.executeUpdate();
            
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }   
    
    public Boolean findUser(String id) throws SQLException {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;
        DatabaseUtility db = new DatabaseUtility();
        
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
                String query = "SELECT * FROM subscribers WHERE subscribeID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, id);
                rs = stat.executeQuery();
                //stat.executeUpdate();

            

            if(rs.absolute(1)){
                            conn.close();
                return true;
            }
            else{
                            conn.close();
                return false;
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        conn.close();
        return false;
    }    
}
