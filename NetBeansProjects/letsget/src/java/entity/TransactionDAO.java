/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.DatabaseUtility;
import services.Registration;

/**
 *
 * @author andrewjacobsen
 */
public class TransactionDAO {
    
    public void add(Transaction tr){
        //insert transaction information into databse
        Connection con = null;
        Statement state = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            //register the driver
            Class.forName(db.getDriver());
            //connect to the database
            con = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            //generate sql statement
            state = con.createStatement();
            String sql = "INSERT INTO transactions (transactionID, orderID, isbn, qty, promoID, total) VALUES (\""
                    + tr.getTransactionID()+ "\", \""//transactionID
                    + tr.getOrderID() + "\", \""//orderID
                    + tr.getISBN() + "\", \""//isbn
                    + tr.getQTY() + "\", \""//qty
                    + tr.getPromoID() + "\", \""//promoID
                    + tr.getTotal() + "\")";//total
            state.executeUpdate(sql);
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
