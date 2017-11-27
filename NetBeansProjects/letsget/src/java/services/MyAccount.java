/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.CartDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author codar_000
 */
public class MyAccount {
    /*    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        HttpSession session = request.getSession(false);
        String userID = (String) session.getAttribute("userID");
        System.out.println(userID);
        if (userID != null) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");

            int qty = Integer.parseInt(request.getParameter("quantity"));
            String refresh = request.getParameter("update");
            UserDAO userDB = new UserDAO();
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
    */
}
