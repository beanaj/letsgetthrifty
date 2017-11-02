package services;

import java.sql.*;
/**
 *
 * @author andrewjacobsen
 */
public class DatabaseUtility {
    //Driver and MySql Server details
    private String JDBC_Driver = "com.mysql.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://letsgetthrifty-database.cgg5mwmrnbyi.us-east-1.rds.amazonaws.com:3306/letsget?zeroDateTimeBehavior=convertToNull";
    private String user = "admin";
    private String pass = "password";
    
    public void DatabaseUtility(){
    }
    
    public String getDriver(){
        return this.JDBC_Driver;
    }
    
    public String getURL(){
        return this.DB_URL;
    }
    
    public String getUser(){
        return this.user;
    }
    
    public String getPass(){
        return this.pass;
    }
}
    

