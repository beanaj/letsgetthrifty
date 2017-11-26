/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrewjacobsen
 */
public class User implements UserI {

    private String userID;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String paymentInfo;
    private String userType;
    private String userPassword;
    private int hash;
    private String orderConfirmationCode;
    private int active;
    
    public User(){ 
    }
    
    public User(String[] info, String accountID, String code, String payment, int hash) {
        //info contains 0-firstName, 1-lastName, 2-phone, 3-email, 4-userType, 5-card, 6-password1, password2
        this.userID = accountID;
        this.firstName = info[0];
        this.lastName = info[1];
        this.phone = info[2];
        this.email = info[3];
        this.userType = info[4];
        this.userPassword = info[6];
        this.hash = hash;
        this.orderConfirmationCode = code;
        this.paymentInfo = "999" + payment;
        this.active = 0;
    }

    public User(String accountID, String type) {
        //type is whether it is an email or by accountID
        UserDAO db = new UserDAO();
        User current = db.getUser(accountID, type);
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
        try {
            if (db.isConfirmed(accountID, "u")) {
                this.active = 1;
            } else {
                this.active = 0;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void register() {
        if (!this.isActive()) {
            UserDAO db = new UserDAO();
            db.addUser(this);
            this.active = 1;
        } else {
            System.out.println("Error in: User.java: at line 65: User is already registered.");
        }
    }

    public boolean isActive() {
        Boolean on = false;
        if (active == 1) {
            on = true;
        }
        return on;
    }

    public String getAccountID() {
        return userID;
    }

    public String getFN() {
        return firstName;
    }

    public String getLN() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return userType;
    }

    public String getPass() {
        return userPassword;
    }

    public int getHash() {
        return hash;
    }

    public String getCode() {
        return orderConfirmationCode;
    }

    public String getPaymentInfo() {
        return paymentInfo;
    }

    public int getActive() {
        return active;
    }
    
    public void setAccountID(String id) {
        this.userID=id;
    }

    public void setFN(String id) {
        this.firstName = id;
    }

    public void setLN(String id) {
        this.lastName = id;
    }

    public void setPhone(String id) {
        this.phone = id;
    }

    public void setEmail(String id) {
        this.email = id;
    }

    public void setType(String id) {
        this.userType = id;
    }

    public void setPass(String id) {
        this.userPassword = id;
    }

    public void setHash(int id) {
        this.hash = id;
    }

    public void setCode(String id) {
        this.orderConfirmationCode = id;
    }

    public void setPaymentInfo(String id) {
        this.paymentInfo = id;
    }

    public void setActive(int id) {
        this.active = id;
    }

};
