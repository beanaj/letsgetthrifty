/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Addison
 */
public class Order {
    private int orderID;
    private int shippingAgencyID;
    private String orderStatus;
    private String orderDate;
    private String shippingAdd;
    private String billingAdd;
    private String paymentMethod;
    private String confNum;
    private String userID;
    private String orderTotal;
    private String cCardID;
    
    //Constructor:
    public Order(int oID, int sAID, String oStat, String oDate, String sAdd, String bAdd, String pay, String conf, String user, String orderT, String cCard) {
        this.orderID = oID;
        this.shippingAgencyID = sAID;
        this.orderStatus = oStat;
        this.orderDate = oDate;
        this.shippingAdd = sAdd;
        this.billingAdd = bAdd;
        this.paymentMethod = pay;
        this.confNum = conf;
        this.userID = user;
        this.orderTotal = orderT;
        this.cCardID = cCard;
    }
    
    public Order() {
        
    }
    
    public void setOrderID(int oID) {
        this.orderID = oID;
    }
    public int getOrderID() {
        return this.orderID;
    }
    public void setShippingAgencyID(int sAID) {
        this.shippingAgencyID = sAID;
    }
    public int getShippingAgencyID() {
        return this.shippingAgencyID;
    }
    public void setOrderStatus(String orderStat) {
        this.orderStatus = orderStat;
    }
    public String getOrderStatus() {
        return this.orderStatus;
    }
    public void setOrderDate(String date) {
        this.orderDate = date;
    }
    public String getOrderDate() {
        return this.orderDate;
    }
    public void setShippingAddress(String shipAdd) {
        this.shippingAdd = shipAdd;
    }
    public String getShippingAddress() {
        return this.shippingAdd;
    }
    public void setBillingAddress(String add) {
        this.billingAdd = add;
    }
    public String getBillingAddress() {
        return this.billingAdd;
    }
    public void setPaymentMethod(String pay) {
        this.paymentMethod = pay;
    }
    public String getPaymentMethod() {
        return this.paymentMethod;
    }
    public void setConfirmationNumber(String cNum) {
        this.confNum = cNum;
    }
    public String getConfirmationNumber() {
        return this.confNum;
    }
    public void setUserID(String uID) {
        this.userID = uID;
    }
    public String getUserID() {
        return this.userID;
    }
    public void setOrderTotal(String oT) {
        this.orderTotal = oT;
    }
    public String getOrderTotal() {
        return this.orderTotal;
    }
    public void setCreditCardID(String cC) {
        this.cCardID = cC;
    }
    public String getCreditCardID() {
        return this.cCardID;
    }
    
    public void addOrder() {
        OrderDAO db = new OrderDAO();
        db.addOrder(this);
    }
}
