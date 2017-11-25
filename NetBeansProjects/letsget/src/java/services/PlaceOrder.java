/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.CartDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class PlaceOrder extends HttpServlet {
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
        
        String shippingStreet = request.getParameter("street");
        String shippingCity = request.getParameter("city");
        String shippingState = request.getParameter("state");
        String shippingZip = request.getParameter("zip");
        String billingStreet = request.getParameter("bstreet");
        String billingCity = request.getParameter("bcity");
        String billingState = request.getParameter("bstate");
        String billingZip = request.getParameter("bzip");
        
        CartDAO cartDB= new CartDAO();
        Double discount = cartDB.getCartPromo(userID);
        
        System.out.println(shippingStreet + "Discount: "+discount);
    }
}
