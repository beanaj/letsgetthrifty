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
public class EndOfDaySales {

    String totalOrders;
    String totalBooksSold;
    String visaSales;
    String masterCardSales;
    String americanExpressSales;
    String discoverSales;
    String capitalOneSales;
    String totalRevenue;
    String totalCosts;
    String totalProfit;

    public EndOfDaySales(Boolean init) {
        if(init){
        this.totalOrders="0";
        this.totalBooksSold = "0";
        this.visaSales = "0.00";
        this.masterCardSales = "0.00";
        this.americanExpressSales = "0.00";
        this.discoverSales = "0.00";
        this.capitalOneSales = "0.00";
        this.totalRevenue = "0.00";
        this.totalCosts = "0.00";
        this.totalProfit = "0.00";
        }
    }

    public String getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(String totalOrders) {
        this.totalOrders = totalOrders;
    }

    public String getTotalBooksSold() {
        return totalBooksSold;
    }

    public void setTotalBooksSold(String totalBooksSold) {
        this.totalBooksSold = totalBooksSold;
    }

    public String getVisaSales() {
        return visaSales;
    }

    public void setVisaSales(String visaSales) {
        this.visaSales = visaSales;
    }

    public String getMasterCardSales() {
        return masterCardSales;
    }

    public void setMasterCardSales(String masterCardSales) {
        this.masterCardSales = masterCardSales;
    }

    public String getAmericanExpressSales() {
        return americanExpressSales;
    }

    public void setAmericanExpressSales(String americanExpressSales) {
        this.americanExpressSales = americanExpressSales;
    }

    public String getDiscoverSales() {
        return discoverSales;
    }

    public void setDiscoverSales(String discoverSales) {
        this.discoverSales = discoverSales;
    }

    public String getCapitalOneSales() {
        return capitalOneSales;
    }

    public void setCapitalOneSales(String capitalOneSales) {
        this.capitalOneSales = capitalOneSales;
    }

    public String getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(String totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(String totalCosts) {
        this.totalCosts = totalCosts;
    }

    public String getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(String totalProfit) {
        this.totalProfit = totalProfit;
    }
    
    
    

}
