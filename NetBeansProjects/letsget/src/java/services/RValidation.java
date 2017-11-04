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
        Boolean valid = true;
        //start validation of user submission content for:
        //firstName, lastName, phone, email, userType, password1, password2
        //check firstname to ensure valid 
        String firstName = information[0];
        if(firstName != null){
            if(firstName.length()<=1||firstName.length()>30){
                error += "<b>Invalid First Name</b><br>(Must be between 2 and 30 characters)<br><br>";
                valid = false; 
            }
        }else{
            error += "<b>Invalid First Name</b><br><br><br>";
            valid = false;
        }
        //validating the last name by length
        String lastName = information[1]; 
        if(lastName != null){
            if(lastName.length()<=1||lastName.length()>30){
                error += "<b>Invalid Last Name</b><br>(Must be between 2 and 30 characters)<br><br>";
                valid = false;
            }
        }else{
            error += "<b>Invalid Last Name</b><br><br><br>";
            valid = false;
        }
        //validating the phone number by replacing anything that isn't a number and then checking by length
        String phone = information[2];
        phone = phone.replaceAll( "[^\\d]", "" ); 
        if(phone != null){
            if(phone.length()!=10){
                error += "<b>Invalid Phone Number</b><br>(Must be 10 digits in XXX-XXX-XXXX format)<br><br>";
                valid = false;
            }
        }else{
            error += "<b>Invalid Phone Number</b><br>(Must be 10 digits in XXX-XXX-XXXX format)<br><br>";
            valid = false;
        }
        
        //checking to ensure email at least has an @
        String email = information[3];
        int atSymbol = email.indexOf('@');
        if(email != null){
            if(atSymbol == -1){
                error += "<b>Invalid Email Address</b><br><br>";
                valid = false;
            }
        }else{
            error += "<b>Invalid Email Address</b><br><br>";
            valid = false;
        }
        //checking to make sure passwords are long enough and match
        String password1 = information[5];
        String password2 = information[6];
        
        if(password1 != null && password2 != null){
            if(!password1.equals(password2)){
                error += "<b>Invalid Password</b><br>(Passwords must match)<br><br>";
                valid = false;
            }else if(password1.length()<5){
                error += "<b>Invalid Password</b><br>(Password must be at least 5 characters)<br><br>";
                valid = false;
            }
        }else{
            error += "<b>Invalid Password</b><br><br>";
            valid = false;
        }
        
        return valid;
    }
    
    @Override
    public String errorMessage(){
        return error;
    }
}
