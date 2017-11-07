/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Addison
 */

package entity;

public class Book {
   private String isbn;
   private String genre;
   private String author;
   private String title;
   private double rating;
   private String picture;
   private int edition;
   private String publisher;
   private int publicationYear;
   private int qtyInStock;
   private int minThreshold;
   private double buyPrice;
   private double sellPrice;
   private int supplierID;
   
   public String getISBN() {
       return this.isbn;
   }
   public void setISBN(String str) {
       this.isbn = str;
   }
   public String getGenre() {
       return this.genre;
   }
   public void setGenre(String str) {
       this.genre = str;
   }
   public String getAuthor() {
       return this.author;
   }
   public void setAuthor(String str) {
       this.author = str;
   }
   public String getTitle() {
       return this.title;
   }
   public void setTitle(String str) {
       this.title = str;
   }
   public Double getRating() {
       return this.rating;
   }
   public void setRating(int rat) {
       this.rating = rat;
   }
   public String getPicture() {
       return this.picture;
   }
   public void setPicture(String str) {
       this.picture = str;
   }
   public int getEdition() {
       return this.edition;
   }
   public void setEdition(int edition) {
       this.edition = edition;
   }
   public String getPublisher() {
       return this.publisher;
   }
   public void setPublisher(String str) {
       this.publisher = str;
   }
   public int getPublicationYear() {
       return this.publicationYear;
   }
   public void setPublicationYear(int pYear) {
       this.publicationYear = pYear;
   }
   public int getQtyInStock() {
       return this.qtyInStock;
   }
   public void setQtyInStock(int qty) {
       this.qtyInStock = qty;
   }
   public int getMinThreshold() {
       return this.minThreshold;
   }
   public void setMinThreshold(int minThresh) {
       this.minThreshold = minThresh;
   }
   public double getBuyPrice() {
       return this.buyPrice;
   }
   public void setBuyPrice(double buyP) {
       this.buyPrice = buyP;
   }
   public double getSellPrice() {
       return this.sellPrice;
   }
   public void setSellPrice(double sellP) {
       this.sellPrice = sellP;
   }
   public int getSupplierID() {
       return this.supplierID;
   }
   public void setSupplierID(int supID) {
       this.supplierID = supID;
   }
}
