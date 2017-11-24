/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
                    + encrypt(user.getPass()) + "\", \""//userpass
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

    public String encrypt(String pass){
        byte byteData[] = {};
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pass.getBytes());

            byteData = md.digest();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
    }
    
    //get user info from database
    public User getUser(String accountID, String type){
        User user = new User();
        //need to fetch all info from database, make a user and return it 
        Connection con;
        Statement state;
        DatabaseUtility db = new DatabaseUtility();
        if(type.equals("e")){
            type = "email";
        }else{
            type = "userID";
        }
        try {
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            state = con.createStatement();
            String sql = "SELECT * FROM users WHERE " + type + " = "+"'"+accountID+"'";
            ResultSet result = state.executeQuery(sql);
            String userID = null, firstName = null, lastName = null, email = null;
            String phone = null, paymentInfo = null, userType = null, userPassword = null, orderConfirmationCode = null;
            int hash = -1, active = -1;
            
            while(result.next()){
                  userID = result.getString("userID");
                  firstName = result.getString("firstName");
                  lastName = result.getString("lastName");
                  email = result.getString("email");
                  phone = result.getString("phone");
                  paymentInfo = result.getString("paymentInfo");
                  userType = result.getString("userType");
                  userPassword = result.getString("userPassword");
                  hash = result.getInt("hash");
                  orderConfirmationCode = result.getString("orderConfirmationCode");
                  active = result.getInt("active");
            }
            
            user.setAccountID(userID);
            user.setFN(firstName);
            user.setLN(lastName);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPaymentInfo(paymentInfo);
            user.setType(userType);
            user.setPass(userPassword);
            user.setHash(hash);
            user.setCode(orderConfirmationCode);
            user.setActive(active);
            
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    public Boolean isRegistered(String accountID, String type) throws ClassNotFoundException{
        Boolean found;
        Connection con;
        Statement state;
        DatabaseUtility db = new DatabaseUtility();
        if(type.equals("e")){
            type = "email";
        }else{
            type = "userID";
        }
        try {
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            state = con.createStatement();
            String sql = "SELECT * FROM users WHERE " + type +" = "+"'"+accountID+"'";
            ResultSet result = state.executeQuery(sql);
            if (!result.isBeforeFirst() ) {    
                 found = false;
            } else{
                found = true;
            }
            
        } catch (SQLException exception) {
            System.out.println("not found");
            found = false;
        } 
        return found;
    }
    
    public Boolean isConfirmed(String accountID, String type) throws ClassNotFoundException{
        Boolean found;
        Connection con;
        Statement state;
        DatabaseUtility db = new DatabaseUtility();
        if(type.equals("e")){
            type = "email";
        }else{
            type = "userID";
        }

        try {
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            state = con.createStatement();
            String sql = "SELECT * FROM users WHERE " + type +" = "+"'"+accountID+"'";

            ResultSet result = state.executeQuery(sql);
            String active = "";
            while(result.next()){
                active = result.getString("active");
            }
            
            if(active.equals("1")){
                found = true;
            }else{
                found = false;
            }
        } catch (SQLException exception) {
            found = false;
        } 
        return found;
    }
    
    public List<User> list() throws SQLException {
        DatabaseUtility db = new DatabaseUtility();
        
        List<User> users = new ArrayList<User>();
        
        try {
            Class.forName(db.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try (
            Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = statement.executeQuery();
                ) {
            while (rs.next()) {
                User user = new User();
                user.setAccountID(rs.getString("userID"));
                user.setFN(rs.getString("firstName"));
                user.setLN(rs.getString("lastName"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setPaymentInfo(rs.getString("paymentInfo"));
                user.setType(rs.getString("userType"));
                user.setPass(rs.getString("userPassword"));
                user.setHash(rs.getInt("hash"));
                user.setCode(rs.getString("orderConfirmationCode"));
                user.setActive(rs.getInt("active"));
                
                users.add(user);
            }
        
        }
        return users;
    }
    
}
