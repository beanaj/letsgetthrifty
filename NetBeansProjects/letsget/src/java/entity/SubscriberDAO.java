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
    
}
