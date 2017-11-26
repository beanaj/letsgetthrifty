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
   
   //Constructor
   public Book(String i, String g, String a, String t, double r, String pic, int e, String pub, int pubYear, int q, int minT, double bp, double sp, int sID) {
       this.isbn = i;
       this.genre = g;
       this.author = a;
       this.title = t;
       this.rating = r;
       this.picture = pic;
       this.edition = e;
       this.publisher = pub;
       this.publicationYear = pubYear;
       this.qtyInStock = q;
       this.minThreshold = minT;
       this.buyPrice = bp;
       this.sellPrice = sp;
       this.supplierID = sID;
   }
   
   public Book(String isbn){
       Book dbBook = new Book();
       BookDAO db = new BookDAO();
       dbBook= db.getBookByID(isbn);
       this.isbn = dbBook.getISBN();
       this.genre = dbBook.getGenre();
       this.author = dbBook.getAuthor();
       this.title = dbBook.getTitle();
       this.rating = dbBook.getRating();
       this.picture = dbBook.getPicture();
       this.edition = dbBook.getEdition();
       this.publisher = dbBook.getPublisher();
       this.publicationYear = dbBook.getPublicationYear();
       this.qtyInStock = dbBook.getQtyInStock();
       this.minThreshold = dbBook.getMinThreshold();
       this.buyPrice = dbBook.getBuyPrice();
       this.sellPrice = dbBook.getSellPrice();
       this.supplierID = dbBook.getSupplierID();
       
   }
   
   public Book() {
       
   }
   
   public void addBook() {
       BookDAO db = new BookDAO();
       db.addBook(this);
   }
   
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
