/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author andrewjacobsen
 */
public class RValidation implements IValidator{
    private String error = "";
    @Override
    public Boolean isValid(String[] information){
        Boolean valid = false;
        
        
        return valid;
    }
    @Override
    public String errorMessage(){
        return error;
    }
}
