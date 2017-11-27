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
public class PromotionDAO {

    public List<Promotion> list() throws SQLException {
        DatabaseUtility db = new DatabaseUtility();

        List<Promotion> promotions = new ArrayList<Promotion>();

        try {
            Class.forName(db.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (
                Connection connection = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM promotions");
                ResultSet rs = statement.executeQuery();) {
            while (rs.next()) {
                Promotion promo = new Promotion();
                promo.setPromoID(rs.getInt("promoID"));
                promo.setPromoName(rs.getString("promoName"));
                promo.setPercentage(rs.getDouble("percentage"));
                promo.setExpiration(rs.getString("expiration"));

                promotions.add(promo);
            }
            connection.close();
        }
        return promotions;
    }

    public void addPromo(Promotion p) {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());
            String query = "INSERT INTO promotions (promoID, promoName, percentage, expiration) VALUES (?, ?, ?, ?)";
            stat = conn.prepareStatement(query);
            stat.setInt(1, p.getPromoID());
            stat.setString(2, p.getPromoName());
            stat.setDouble(3, p.getPercentage());
            stat.setString(4, p.getExpiration());

            stat.execute();
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePromotion(int pID, String name, double perc, String exp) {
        //Set up database connection:
        Connection conn = null;
        PreparedStatement stat = null;
        DatabaseUtility db = new DatabaseUtility();

        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(db.getURL(), db.getUser(), db.getPass());

            if (!name.isEmpty()) {
                String query = "UPDATE promotions SET promoName = ? WHERE promoID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, name);
                stat.setInt(2, pID);
                stat.executeUpdate();
            }
            if (Double.compare(perc, -1) != 0) {
                String query = "UPDATE promotions SET percentage = ? WHERE promoID = ?";
                stat = conn.prepareStatement(query);
                stat.setDouble(1, perc);
                stat.setInt(2, pID);
                stat.executeUpdate();
            }
            if (!exp.isEmpty()) {
                String query = "UPDATE promotions SET expiration = ? WHERE promoID = ?";
                stat = conn.prepareStatement(query);
                stat.setString(1, exp);
                stat.setInt(2, pID);
                stat.executeUpdate();
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
