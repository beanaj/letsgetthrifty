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
    
    public Payment(String accountID){
        PaymentDAO db = new PaymentDAO();
        String payID = "999"+accountID.replaceAll( "[^\\d]", "" ); 
        Payment pay = db.getPaymentByID(payID);
        this.creditCardID=pay.getCCID();
        this.creditCardNumber=pay.getNum();
        this.creditCardType=pay.getType();
        this.expirationDate=pay.getExp();
        this.userID=pay.getUserID();
    }

    public Payment() {
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
    
    public void setCCID(String id){
        this.creditCardID=id;
    }
    
    public void setUserID(String id){
        this.userID = id;
    }
    
    public void setNum(String id){
        this.creditCardNumber=id;
    }
    
    public void setType(String id){
        this.creditCardType=id;
    }
    
    public void setExp(String id){
        this.expirationDate=id;
    }
    
}
