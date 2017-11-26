/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import services.DatabaseUtility;

/**
 *
 * @author Addison
 */
public class OrderDAO {

    public List<Order> list() throws SQLException {
        DatabaseUtility db = new DatabaseUtility();

        List<Order> orders = new ArrayList<Order>();

        try {
            Class.forName(db.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
                ResultSet rs = statement.executeQuery();) {
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("orderID"));
                order.setShippingAgencyID(rs.getInt("shippingAgencyID"));
                order.setOrderStatus(rs.getString("orderStatus"));
                order.setOrderDate(rs.getString("orderDate"));
                order.setShippingAddress(rs.getString("shippingAddress"));
                order.setBillingAddress(rs.getString("billingAdress"));
                order.setPaymentMethod(rs.getString("paymentMethod"));
                order.setConfirmationNumber(rs.getInt("confirmationNumber"));
                order.setUserID(rs.getString("userID"));
                order.setOrderTotal(rs.getString("orderTotal"));
                order.setCreditCardID(rs.getString("creditCardID"));

                orders.add(order);
            }

        }
        return orders;
    }

    //Add a book to the database
    public void addOrder(Order o) {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            String query = "INSERT INTO orders (orderID, shippingAgencyID, orderStatus, orderDate, shippingAddress, billingAdress, paymentMethod, confirmationNumber, userID, orderTotal, creditCardID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stat = conn.prepareStatement(query);
            stat.setInt(1, o.getOrderID());
            stat.setInt(2, o.getShippingAgencyID());
            stat.setString(3, o.getOrderStatus());
            stat.setString(4, o.getOrderDate());
            stat.setString(5, o.getShippingAddress());
            stat.setString(6, o.getBillingAddress());
            stat.setString(7, o.getPaymentMethod());
            stat.setInt(8, o.getConfirmationNumber());
            stat.setString(9, o.getUserID());
            stat.setString(10, o.getOrderTotal());
            stat.setString(11, o.getCreditCardID());
            stat.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkIDValidity(int orderID){
        boolean orderFound =false;
        DatabaseUtility db = new DatabaseUtility();
        try {
            Class.forName(db.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE orderID = '"+orderID+"'");
                ResultSet rs = statement.executeQuery();) {
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(rs.getInt("orderID"));
                orderFound=true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderFound;
    }
}
