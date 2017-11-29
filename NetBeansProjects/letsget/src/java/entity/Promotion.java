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
public class Promotion {
    private int promoID;
    private String promoName;
    private double percentage;
    private String expiration;
    
    //Constructor
    public Promotion(int promo, String name, double perc, String exp) {
        this.promoID = promo;
        this.promoName = name;
        this.percentage = perc;
        this.expiration = exp;
    }
    
    public Promotion() {
        
    }
    
    public int getPromoID() {
        return this.promoID;
    }
    public void setPromoID(int promo) {
        this.promoID = promo;
    }
    public String getPromoName() {
        return this.promoName;
    }
    public void setPromoName(String name) {
        this.promoName = name;
    }
    public double getPercentage() {
        return this.percentage;
    }
    public void setPercentage(double perc) {
        this.percentage = perc;
    }
    public String getExpiration() {
        return this.expiration;
    }
    public void setExpiration(String exp) {
        this.expiration = exp;
    }
    
    public void addPromo() throws SQLException {
        PromotionDAO db = new PromotionDAO();
        db.addPromo(this);
    }
}
