package entity;


import entity.CartObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.DatabaseUtility;
import services.Registration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author andrewjacobsen
 */
public class CartDAO {

    public CartObject[] getCartContents(String userID) {
        ArrayList<CartObject> cl = new ArrayList();
        Connection con = null;
        Statement state = null;
        ResultSet result = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            state = con.createStatement();
            String sql = "SELECT * FROM carts WHERE userID = '" + userID + "'";
            result = state.executeQuery(sql);
            while (result.next()) {
                CartObject book = new CartObject();
                book.isbn=result.getString("isbn");
                book.quantity=Integer.parseInt(result.getString("qty"));
                cl.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this should be improved if the book quantity is large
        for(int i =0; i < cl.size(); i++){
            CartObject book = cl.get(i);
            for(int j=0; j<cl.size(); j++){
                if(i<=j&&book.isEqual(cl.get(j))){
                    book.quantity += cl.get(j).quantity;
                    System.out.println(i+"  "+j);
                    cl.set(i, book);
                    cl.remove(j);
                }
            }
        }
        
        CartObject[] cart = new CartObject[cl.size()];
        for(int i =0; i< cl.size(); i++){
            cart[i]=cl.get(i);
        }
        return cart;
    }
}
