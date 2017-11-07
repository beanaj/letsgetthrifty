/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author andrewjacobsen
 */
public class User implements UserI{
    private String userID;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String paymentInfo;
    private String userType;
    private String userPassword;
    private String hash;
    private String orderConfirmationCode;
    private int active;
    
    @Override
    public void User(String[] info, String accountID, String code, String payment, String hash){
        //info contains firstName, lastName, phone, email, userType, password1, password2
        this.userID = accountID;
        this.firstName = info[0];
        this.lastName = info[1];
        this.phone = info[2];
        this.email = info[3];
        this.userType = info[4];
        this.userPassword = info[5];
        this.hash = hash;
        this.orderConfirmationCode = code;
        this.paymentInfo = payment;
        this.active = 0;
    }
    

    @Override
    public void User(String accountID){
            UserDAO db = new UserDAO();
            User current = db.getUser(accountID);
            this.userID = current.getAccountID();
            this.firstName = current.getFN();
            this.lastName = current.getLN();
            this.phone = current.getPhone();
            this.email = current.getEmail();
            this.userType = current.getType();
            this.userPassword = current.getPass();
            this.hash = current.getHash();
            this.orderConfirmationCode = current.getCode();
            this.paymentInfo = current.getPaymentInfo();
            this.active = 1;
    }


    @Override
    public void register(){
        if(!this.isActive()){
            UserDAO db = new UserDAO();
            db.addUser(this);
            this.active = 1;
        }else{
            System.out.println("Error in: User.java: at line 65: User is already registered.");
        }
    }
   
    
    public boolean isActive(){
        Boolean on = false;
        if(active == 1){
            on = true;
        }
        return on;
    }
    
     
    public String getAccountID(){
        return userID;
    }
    
    public String getFN(){
        return firstName;
    }
    
    public String getLN(){
        return lastName;
    }
    
    public String getPhone(){
        return phone;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getType(){
        return userType;
    }
    
    public String getPass(){
        return userPassword;
    }
    
    public String getHash(){
        return hash;
    }
    
    public String getCode(){
        return orderConfirmationCode;
    }
    
    public String getPaymentInfo(){
        return paymentInfo;
    }

    public int getActive(){
        return active;
    }
    
    
};
