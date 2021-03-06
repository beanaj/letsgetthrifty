/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.ShippingAgency;
import entity.ShippingAgencyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Addison
 */
@WebServlet(name = "AgencyTable", urlPatterns = {"/agency"})
public class AgencyTable extends HttpServlet {


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
            case "Add Agency":
                int saID = Integer.parseInt(request.getParameter("new_shippingAgencyID"));
                String aName = request.getParameter("new_agencyName");
                String phone = request.getParameter("new_phone");
                String cName = request.getParameter("new_contactName");
                String cPhone = request.getParameter("new_contactPhone");

                ShippingAgency agency = new ShippingAgency(saID, aName, phone, cName, cPhone);
               
                try {
                    agency.addAgency();
                } catch (SQLException ex) {
                    String error = "Error: Invalid input, please ensure your ID is unique.";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("adminAgencies.jsp").forward(request, response);
                }
            
                break;
            case "Update Agency":
                int saIDB = -1;
                String aNameB = "";
                String phoneB = "";
                String cNameB = "";
                String cPhoneB = ""; 
                
                if (!request.getParameter("new_shippingAgencyID").isEmpty()) {
                    saIDB = Integer.parseInt(request.getParameter("new_shippingAgencyID"));
                } 
                if (!request.getParameter("new_agencyName").isEmpty()) {
                    aNameB = request.getParameter("new_agencyName");
                } 
                if (!request.getParameter("new_phone").isEmpty()) {
                    phoneB = request.getParameter("new_phone");
                } 
                if (!request.getParameter("new_contactName").isEmpty()) {
                    cNameB = request.getParameter("new_contactName");
                } 
                if (!request.getParameter("new_contactPhone").isEmpty()) {
                    cPhoneB = request.getParameter("new_contactPhone");
                } 
                
                //Update agency in the database:
                ShippingAgencyDAO db = new ShippingAgencyDAO();
                try {
                    db.updateAgency(saIDB, aNameB, phoneB, cNameB, cPhoneB);
                } catch (SQLException ex) {
                    String error = "Error: Invalid input";
                    request.setAttribute("error", error);
                    request.getRequestDispatcher("adminAgencies.jsp").forward(request, response);
                }
                
                break;
        } // switch
        response.sendRedirect("adminAgencies.jsp");
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
