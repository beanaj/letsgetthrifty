/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.UserDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrewjacobsen
 */
public class LValidation implements IValidator {

    private String error="";

    @Override
    public Boolean isValid(String[] information) {
        Boolean valid = true;
        //this string will contain 
        //[0] = the type of the login
        //[1] = the actual login id
        //[2] = the password
        String type = information[0];
        String id = information[1];
        String password = information[2];
        UserDAO uDAO = new UserDAO();
        Boolean isRegistered = false;
        Boolean isConfirmed = false;
        //check to see if the user is registered
        try {
            isRegistered = uDAO.isRegistered(id, type);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LValidation.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (isRegistered) {
            //check to see if user is confirmed
            try {
                isConfirmed = uDAO.isConfirmed(id, type);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LValidation.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(isConfirmed){
                
            }else{
                error+= "<b>Invalid AccountID/Email</b><br>Please confirm your account";
                valid = false;
            }

        } else {
            error += "<b>Invalid AccountID/Email or Password</b><br>Please enter a valid accountID/email or password";
            valid = false;
        }

        return valid;
    }

    @Override
    public String errorMessage() {
        return error;
    }

}
