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
public class Payment implements PaymentI{
    String creditCardID;
    String userID;
    String creditCardNumber;
    String creditCardType;
    String expirationDate;
    
    public Payment(String accountID, String payment, String card, String exp, String type){
        this.creditCardID = "999"+payment.replaceAll( "[^\\d]", "" ); 
        this.userID = accountID;
        this.creditCardNumber = card;
        this.creditCardType = type;
        this.expirationDate = exp;
    }
    
    @Override
    public void submit() {
            PaymentDAO db = new PaymentDAO();
            db.addPayment(this);
    }
    
    public String getCCID(){
        return this.creditCardID;
    }
    
    public String getUserID(){
        return this.userID;
    }
    
    public String getNum(){
        return this.creditCardNumber;
    }
    
    public String getType(){
        return this.creditCardType;
    }
    
    public String getExp(){
        return this.expirationDate;
    }
    
}
