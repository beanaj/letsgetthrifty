/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import services.DatabaseUtility;
/**
 *
 * @author andrewjacobsen
 */
public class UserDAO {
    private DatabaseUtility db;
    
    //add user to database
    public void addUser(User user){
        
    }
    
    //get user info from database
    public User getUser(String accountID){
        User butt = new User();//need to fetch all info from database, make a user, set active and return it
        return butt;
    }
    
}
