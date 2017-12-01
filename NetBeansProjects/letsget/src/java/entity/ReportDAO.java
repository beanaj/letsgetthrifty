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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.DatabaseUtility;

/**
 *
 * @author andrewjacobsen
 */
public class ReportDAO {

    String getTotalOrders() {
        String total = "0";
        int to = 0;
        Connection con = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
            ResultSet rs = statement.executeQuery();
            {
                while (rs.next()) {
                    String date = rs.getString("orderDate");
                    Date current = new Date();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date order = df.parse(date);

                    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                    if (fmt.format(order).equals(fmt.format(current))) {
                        to++;
                    }

                }
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        total = "" + to;
        return total;
    }

    String getTotalBooks() {
        String total = "0";
        int to = 0;
        DatabaseUtility db = new DatabaseUtility();
        try {
            Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
            ResultSet rs = statement.executeQuery();
            {
                while (rs.next()) {
                    String date = rs.getString("orderDate");
                    Date current = new Date();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date order = df.parse(date);
                    String orderID = rs.getString("orderID");
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                    if (fmt.format(order).equals(fmt.format(current))) {
                        try {
                            Connection conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
                            PreparedStatement stat = connection.prepareStatement("SELECT * FROM transactions WHERE orderID = '" + orderID + "'");
                            ResultSet rse = stat.executeQuery();
                            {
                                while (rse.next()) {
                                    to += rse.getInt("qty");
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        total = "" + to;
        return total;
    }

    String[] getCardTotals() {
        String[] totals = new String[5];//0visa 1master 2amex 3discover 4capital
        int visa = 0;
        int master = 0;
        int amex = 0;
        int discover = 0;
        int capital = 0;
        Connection con = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
            ResultSet rs = statement.executeQuery();
            {
                while (rs.next()) {
                    String date = rs.getString("orderDate");
                    Date current = new Date();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date order = df.parse(date);

                    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                    if (fmt.format(order).equals(fmt.format(current))) {
                        String type = rs.getString("paymentMethod");
                        switch (type) {
                            case "Visa":
                                visa++;
                                break;
                            case "Master Card":
                                master++;
                                break;
                            case "American Express":
                                amex++;
                                break;
                            case "Discover":
                                discover++;
                                break;
                            case "Capital One":
                                capital++;
                                break;
                        }
                    }

                }
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        totals[0] = "" + visa;
        totals[1] = "" + master;
        totals[2] = "" + amex;
        totals[3] = "" + discover;
        totals[4] = "" + capital;
        return totals;
    }

    double getTotalRevenue() {
        String total = "0";
        double to = 0;
        DatabaseUtility db = new DatabaseUtility();
        try {
            Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
            ResultSet rs = statement.executeQuery();
            {
                while (rs.next()) {
                    String date = rs.getString("orderDate");
                    Date current = new Date();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date order = df.parse(date);
                    String orderID = rs.getString("orderID");
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                    if (fmt.format(order).equals(fmt.format(current))) {
                        to += rs.getDouble("orderTotal");
                        System.out.println(to);
                    }

                }
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return to;
    }

    Double getTotalCosts() {
        double to = 0;
        DatabaseUtility db = new DatabaseUtility();
        try {
            Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");
            ResultSet rs = statement.executeQuery();
            {
                while (rs.next()) {
                    String date = rs.getString("orderDate");
                    Date current = new Date();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date order = df.parse(date);
                    String orderID = rs.getString("orderID");
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
                    if (fmt.format(order).equals(fmt.format(current))) {
                        try {
                            PreparedStatement stat = connection.prepareStatement("SELECT * FROM transactions WHERE orderID = '" + orderID + "'");
                            ResultSet rse = stat.executeQuery();
                            {
                                while (rse.next()) {
                                    String isbn = rse.getString("isbn");
                                    try {
                                        PreparedStatement s = connection.prepareStatement("SELECT * FROM books WHERE isbn = '" + isbn + "'");
                                        ResultSet r = s.executeQuery();
                                        {
                                            while (r.next()) {
                                                to += r.getDouble("buyPrice");
                                            }
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return to;
    }

}
