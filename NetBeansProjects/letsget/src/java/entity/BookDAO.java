
package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        }
        return books;
    }
    
    //Add a book to the database
    public void addBook(Book b) {
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

