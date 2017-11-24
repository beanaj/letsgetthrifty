/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Promotion;
import entity.PromotionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Addison
 */
public class PromotionTable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PromotionTable</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PromotionTable at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String hiddenParam = request.getParameter("addUpdate");
        
        switch (hiddenParam) {
            case "Add Promotion":
                int promoID = Integer.parseInt(request.getParameter("new_promoID"));
                String promoName = request.getParameter("new_promoName");
                double perc = Double.parseDouble(request.getParameter("new_percentage"));
                String exp = request.getParameter("new_expiration");

                Promotion promo = new Promotion(promoID, promoName, perc, exp);
                promo.addPromo();
                
                break;
            case "Update Promotion":
                int pID = -1;
                String name = "";
                double per = -1;
                String expi = "";
                 
                
                if (!request.getParameter("new_promoID").isEmpty()) {
                    pID = Integer.parseInt(request.getParameter("new_promoID"));
                } 
                if (!request.getParameter("new_promoName").isEmpty()) {
                    name = request.getParameter("new_promoName");
                } 
                if (!request.getParameter("new_percentage").isEmpty()) {
                    per = Double.parseDouble(request.getParameter("new_percentage"));
                } 
                if (!request.getParameter("new_expiration").isEmpty()) {
                    expi = request.getParameter("new_expiration");
                }
                
                //Update agency in the database:
                PromotionDAO db = new PromotionDAO();
                db.updatePromotion(pID, name, per, expi);
                
                break;
        } // switch
        response.sendRedirect("adminPromotions.jsp");
        out.close();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
