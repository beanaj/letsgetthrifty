/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.media.jfxmedia.logging.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Book;
import entity.BookDAO;

/**
 *
 * @author Addison
 */
public class BookTable extends HttpServlet {

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
            case "Add Book":
                String isbn = request.getParameter("new_isbn");
                String genre = request.getParameter("new_genre");
                String author = request.getParameter("new_author");
                String title = request.getParameter("new_title");
                double rating = Double.parseDouble(request.getParameter("new_rating"));
                String picture = request.getParameter("new_picture");
                int edition = Integer.parseInt(request.getParameter("new_edition"));
                String pub = request.getParameter("new_publisher");
                int pubYear = Integer.parseInt(request.getParameter("new_publicationyear"));
                int quantity = Integer.parseInt(request.getParameter("new_quantity"));
                int minT = Integer.parseInt(request.getParameter("new_minthreshold"));
                double buyPrice = Double.parseDouble(request.getParameter("new_buyprice"));
                double sellPrice = Double.parseDouble(request.getParameter("new_sellprice"));
                int supplierID = Integer.parseInt(request.getParameter("new_supplierID"));

                //Create Book object and add to database:
                Book book = new Book(isbn, genre, author, title, rating, picture, edition, pub, pubYear, quantity, minT, buyPrice, sellPrice, supplierID);
                book.addBook();
                break;
            case "Update Book":
                String i = "";
                String g = "";
                String a = "";
                String t = "";
                double r = -1;
                String p = "";
                int e = -1;
                String pu = "";
                int pubY = -1;
                int q = -1;
                int m = -1;
                double b = -1;
                double s = -1;
                int sup = -1;
                
                if (!request.getParameter("new_isbn").isEmpty()) {
                    i = request.getParameter("new_isbn");
                } 
                if (!request.getParameter("new_genre").isEmpty()) {
                    g = request.getParameter("new_genre");
                } 
                if (!request.getParameter("new_author").isEmpty()) {
                    a = request.getParameter("new_author");
                } 
                if (!request.getParameter("new_title").isEmpty()) {
                    t = request.getParameter("new_title");
                }
                if (!request.getParameter("new_rating").isEmpty()) {
                    r = Double.parseDouble(request.getParameter("new_rating"));
                } 
                if (!request.getParameter("new_picture").isEmpty()) {
                    p = request.getParameter("new_picture");
                } 
                if (!request.getParameter("new_edition").isEmpty()) {
                    e = Integer.parseInt(request.getParameter("new_edition"));
                } 
                if (!request.getParameter("new_publisher").isEmpty()) {
                    pu = request.getParameter("new_publisher");
                }
                if (!request.getParameter("new_publicationyear").isEmpty()) {
                    pubY = Integer.parseInt(request.getParameter("new_publicationyear"));
                }
                if (!request.getParameter("new_quantity").isEmpty()) {
                    q = Integer.parseInt(request.getParameter("new_quantity"));
                }
                if (!request.getParameter("new_minthreshold").isEmpty()) {
                    m = Integer.parseInt(request.getParameter("new_minthreshold"));
                }
                if (!request.getParameter("new_buyprice").isEmpty()) {
                    b = Double.parseDouble(request.getParameter("new_buyprice"));
                }
                if (!request.getParameter("new_sellprice").isEmpty()) {
                    s = Double.parseDouble(request.getParameter("new_sellprice"));
                }
                if (!request.getParameter("new_supplierID").isEmpty()) {
                    sup = Integer.parseInt(request.getParameter("new_supplierID"));
                }
                
                //Update book in the database:
                BookDAO db = new BookDAO();
                System.out.println(r);
                db.updateBook(i, g, a, t, r, p, e, pu, pubY, q, m, b, s, sup);
                
                break;
        } // switch
        
        response.sendRedirect("adminInventory.jsp");
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
