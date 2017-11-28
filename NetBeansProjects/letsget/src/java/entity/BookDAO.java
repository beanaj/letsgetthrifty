
package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.*;
import services.DatabaseUtility;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Addison
 */
public class BookDAO {
    
    public Book getBookByID(String isbn){
        DatabaseUtility db = new DatabaseUtility();
        Book book = new Book();
        try {
            Class.forName(db.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try (
            Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books WHERE isbn = '"+isbn+"'");
            ResultSet rs = statement.executeQuery();
                ) {
            while (rs.next()) {
                book.setAuthor(rs.getString("author"));
                book.setBuyPrice(rs.getDouble("buyPrice"));
                book.setEdition(rs.getInt("edition"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getString("isbn"));
                book.setMinThreshold(rs.getInt("minThreshold"));
                book.setPicture(rs.getString("picture"));
                book.setPublicationYear(rs.getInt("publicationYear"));
                book.setPublisher(rs.getString("publisher"));
                book.setQtyInStock(rs.getInt("qtyInStock"));
                book.setRating(rs.getInt("rating"));
                book.setSellPrice(rs.getDouble("sellPrice"));
                book.setSupplierID(rs.getInt("supplierID"));
                book.setTitle(rs.getString("title"));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }
    
    public List<Book> list() throws SQLException {
        DatabaseUtility db = new DatabaseUtility();
        
        List<Book> books = new ArrayList<Book>();
        
        try {
            Class.forName(db.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try (
            Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM books");
            ResultSet rs = statement.executeQuery();
                ) {
            while (rs.next()) {
                Book book = new Book();
                book.setAuthor(rs.getString("author"));
                book.setBuyPrice(rs.getDouble("buyPrice"));
                book.setEdition(rs.getInt("edition"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getString("isbn"));
                book.setMinThreshold(rs.getInt("minThreshold"));
                book.setPicture(rs.getString("picture"));
                book.setPublicationYear(rs.getInt("publicationYear"));
                book.setPublisher(rs.getString("publisher"));
                book.setQtyInStock(rs.getInt("qtyInStock"));
                book.setRating(rs.getInt("rating"));
                book.setSellPrice(rs.getDouble("sellPrice"));
                book.setSupplierID(rs.getInt("supplierID"));
                book.setTitle(rs.getString("title"));
                
                books.add(book);
            }
            connection.close();
        }
        return books;
    }
    
    //Add a book to the database
    public void addBook(Book b) throws SQLException {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            String query = "INSERT INTO books (isbn, genre, author, title, rating, picture, edition, publisher, publicationYear, qtyInStock, minThreshold, buyPrice, sellPrice, supplierID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stat = conn.prepareStatement(query);
            stat.setString(1, b.getISBN());
            stat.setString(2, b.getGenre());
            stat.setString(3, b.getAuthor());
            stat.setString(4, b.getTitle());
            stat.setDouble(5, b.getRating());
            stat.setString(6, b.getPicture());
            stat.setInt(7, b.getEdition());
            stat.setString(8, b.getPublisher());
            stat.setInt(9, b.getPublicationYear());
            stat.setInt(10, b.getQtyInStock());
            stat.setInt(11, b.getMinThreshold());
            stat.setDouble(12, b.getBuyPrice());
            stat.setDouble(13, b.getSellPrice());
            stat.setInt(14, b.getSupplierID());
            stat.execute();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void updateBook(String is, String gen, String author, String tit, double rat, String pic, int ed, String pub, int pubYear, int qty, int minT, double buyP, double sellP, int supID) throws SQLException {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            
            if (!gen.isEmpty()) {
                String query = "UPDATE books SET genre = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, gen);
                stat.setString(2, is);
                stat.executeUpdate();  
            } 
            if (!author.isEmpty()) {
                String query = "UPDATE books SET author = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, author);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            if (!tit.isEmpty()) {
                String query = "UPDATE books SET title = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, tit);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            if (Double.compare(rat, -1) != 0) {
                String query = "UPDATE books SET rating = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setDouble(1, rat);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            if (!pic.isEmpty()) {
                String query = "UPDATE books SET picture = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, pic);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            if (Integer.compare(ed, -1) != 0) {
                String query = "UPDATE books SET edition = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setInt(1, ed);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            if (!pub.isEmpty()) {
                String query = "UPDATE books SET publisher = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, pub);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            if (Integer.compare(pubYear, -1) != 0) {
                String query = "UPDATE books SET publicationYear = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setInt(1, pubYear);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            if (Integer.compare(qty, -1) != 0) {
                String query = "UPDATE books SET qtyInStock = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setInt(1, qty);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            if (Integer.compare(minT, -1) != 0) {
                String query = "UPDATE books SET minThreshold = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setInt(1, minT);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            if (Double.compare(buyP, -1) != 0) {
                String query = "UPDATE books SET buyPrice = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setDouble(1, buyP);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            if (Double.compare(sellP, -1) != 0) {
                String query = "UPDATE books SET sellPrice = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setDouble(1, sellP);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            if (Integer.compare(supID, -1) != 0) {
                String query = "UPDATE books SET supplierID = ? WHERE isbn = ?";
                stat = conn.prepareStatement(query);
                stat.setInt(1, supID);
                stat.setString(2, is);
                stat.executeUpdate();
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

