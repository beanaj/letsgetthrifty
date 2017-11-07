
package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    private DataSource dataSource;
    
//    public BookDAO(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
    
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
            }
        
        }
        return books;
    }
}

