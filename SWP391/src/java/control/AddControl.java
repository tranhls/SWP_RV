/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AddControl extends HttpServlet {

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
        
        DAO dao = new DAO();
        
        String pname = request.getParameter("name");
        String pdescription = request.getParameter("description");
        String pprice = request.getParameter("price");
        String pCategory = request.getParameter("catName");
        String pImage = request.getParameter("image");
        String pCatID = Integer.toString(dao.getCategoyByName(pCategory).getCatID());
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        int idd = a.getAccountID();
        
        StringBuilder errorMessage = new StringBuilder();

        // Validate name
        if (pname == null || pname.trim().isEmpty()) {
            errorMessage.append("Name cannot be empty. ");
        }

        // Validate description
        if (pdescription == null || pdescription.trim().length() < 5 || pdescription.trim().length() > 500) {
            errorMessage.append("Description must be between 5 and 500 characters. ");
        }

        // Validate price
        try {
            double price = Double.parseDouble(pprice);
            if (price < 1) {
                errorMessage.append("Price must be greater than or equal to 1. ");
            }
        } catch (NumberFormatException e) {
            errorMessage.append("Price must be a valid number. ");
        }

        // Validate image
        if (pImage == null || pImage.trim().isEmpty()) {
            errorMessage.append("Image URL cannot be empty. ");
        }

        // Validate CatID
        try {
            int catID = Integer.parseInt(pCatID);
        } catch (NumberFormatException e) {
            errorMessage.append("CatID must be an integer. ");
        }

        // Validate Category
        if (pCategory == null || pCategory.trim().isEmpty()) {
            errorMessage.append("Category cannot be empty. ");
        }

        // Check for errors
        if (errorMessage.length() > 0) {
            request.setAttribute("error", errorMessage.toString());
            request.getRequestDispatcher("manager").forward(request, response);
        } else {
            // No validation errors, proceed with insertion
            dao.insertProduct(pname, pdescription, pprice, pCategory, pImage, pCatID, idd);
            request.setAttribute("success", "Product added successfully.");
            request.getRequestDispatcher("manager").forward(request, response);
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
        processRequest(request, response);
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
