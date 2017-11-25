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
public class Address implements AddressI{
    public String addressID;
    public String street;
    public String city;
    public String state;
    public String zip;
    public String userID;
    public String shippingAgencyID;
    public String supplierID;
    
    public Address(){
        
    }
    
    public Address(String addressID, String userID){
        AddressDAO addressDB = new AddressDAO();
        Address newA = addressDB.getAddressByID(addressID, userID);
        this.addressID=newA.addressID;
        this.userID=newA.userID;
        this.state=newA.state;
        this.street=newA.street;
        this.zip=newA.zip;
        this.shippingAgencyID=newA.shippingAgencyID;
        this.city=newA.city;
        this.supplierID=newA.supplierID;                
    }
    
    public Address(String accountID, String street, String city, String zip, String state){
        this.addressID = "a"+accountID.replaceAll( "[^\\d]", "" );
        this.street = street;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.userID = accountID;
    }
    
    @Override
    public void submit(String type){
        if(type.equals("u")){//add a user address
            AddressDAO db = new AddressDAO();
            db.addAddressUser(this);
        }     
    }
    
    public String getAddressID(){
        return this.addressID;
    }
    
    public String getStreet(){
        return this.street;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public String getState(){
        return this.state;
    }
    
    public String getZip(){
        return this.zip;
    }
    
    public String getUser(){
        return this.userID;
    }
    
    public String getShipping(){
        return this.shippingAgencyID;
    }
    
    public String getSupplier(){
        return this.supplierID;
    }
}

