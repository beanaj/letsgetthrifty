/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author andrewjacobsen
 */
public class CartManager extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        String userID = (String) session.getAttribute("userID");
        System.out.println(userID);
        if (userID != null) {
            String isbn = request.getParameter("isbn");
            String priceString = request.getParameter("price");
            double price = Double.parseDouble(priceString);

            int qty = Integer.parseInt(request.getParameter("quantity"));
            String refresh = request.getParameter("update");
            CartDAO cartDB = new CartDAO();
            cartDB.removeFromCart(isbn, userID);
            //in this spot remove all and add qty
            addToCart(isbn, price, userID, qty);
            if(refresh.equals("true")){
                response.sendRedirect("my_cart.jsp");
            }else{
                response.sendRedirect("homepage.jsp");
            }
            
        } else {
            response.sendRedirect("login_register.jsp");
        }

    }

    private void addToCart(String isbn, double price, String userID, int qty) {
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
            for (int i = 0; i < qty; i++) {
                String sql = "INSERT INTO carts (userID, isbn, qty, totalPrice) VALUES (\""
                        + userID + "\", \""//userID
                        + isbn + "\", \""//isbn
                        + 1 + "\", \""//qty
                        + price + "\")";//price
                state.executeUpdate(sql);
            }
            con.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
