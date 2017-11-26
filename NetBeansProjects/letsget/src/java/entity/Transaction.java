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
public class Transaction {
    String transactionID;
    String orderID;
    String isbn;
    String qty;
    String promoID;
    String total;
    
    public Transaction(){
        
    }
    
    public Transaction(String orderID){
        
    }

    public Transaction(String[] info){
        //info
        //0 transactionID
        //1 orderID
        //2 isbn
        //3 qty
        //4 promoid
        //5 total
        this.transactionID = info[0];
        this.orderID = info[1];
        this.isbn = info[2];
        this.qty = info[3];
        this.promoID = info[4];
        this.total = info[5];
    }
    
    public void addToDatabase(){
        TransactionDAO db = new TransactionDAO();
        db.add(this);
    }
    
    public String getTransactionID(){
        return this.transactionID;
    }
    
    public String getOrderID(){
        return this.orderID;
    }
    
    public String getISBN(){
        return this.isbn;
    }
    
    public String getQTY(){
        return this.qty;
    }
    
    public String getPromoID(){
        return this.promoID;
    }
    
    public String getTotal(){
        return this.total;
    }
    
    public void setTransactionID(String id){
        this.transactionID=id;
    }
    
    public void setOrderID(String id){
        this.orderID=id;
    }
    
    public void setISBN(String id){
        this.isbn=id;
    }
    
    public void setQty(String id){
        this.qty=id;
    }
    
    public void setPromoID(String id){
        this.promoID=id;
    }   
    
    public void setTotal(String id){
        this.total=id;
    }      
}
