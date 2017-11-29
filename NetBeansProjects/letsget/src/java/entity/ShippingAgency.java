/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.SQLException;

/**
 *
 * @author Addison
 */
public class ShippingAgency {
    private int shippingAgencyID;
    private String agencyName;
    private String phone;
    private String contactName;
    private String contactPhone;
    
    //Constructor
   public ShippingAgency(int id, String agName, String phoneNum, String coName, String coPhone) {
       this.shippingAgencyID = id;
       this.agencyName = agName;
       this.phone = phoneNum;
       this.contactName = coName;
       this.contactPhone = coPhone;
   }
   
   public ShippingAgency() {
       
   }
   
   public int getAgencyID() {
       return this.shippingAgencyID;
   }
   public void setAgencyID(int id) {
       this.shippingAgencyID = id;
   }
   public String getAgencyName() {
       return this.agencyName;
   }
   public void setAgencyName(String name) {
       this.agencyName = name;
   }
   public String getPhone() {
       return this.phone;
   }
   public void setPhone(String phoneNum) {
       this.phone = phoneNum;
   }
   public String getContactName() {
       return this.contactName;
   }
   public void setContactName(String name) {
       this.contactName = name;
   }
   public String getContactPhone() {
       return this.contactPhone;
   }
   public void setContactPhone(String phoneNum) {
       this.contactPhone = phoneNum;
   }
   public void addAgency() throws SQLException {
       ShippingAgencyDAO db = new ShippingAgencyDAO();
       db.addAgency(this);
   }
}
