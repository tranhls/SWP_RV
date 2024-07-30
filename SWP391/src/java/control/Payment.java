/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Cart;
import entity.voucher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author buidu
 */
public class Payment extends HttpServlet {

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
        DAO d = new DAO();

        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("code");
        voucher vpercent = d.getPercent(code);
        session.setAttribute("vpercent", 0);

//        session.setAttribute("voucherPercent", vpercent);
        Account account = (Account) session.getAttribute("acc");
        List<Cart> carts = d.listCart(account.getAccountID());

        double totalPrice = 0;
        for (Cart item : carts) {
            totalPrice += item.getProduct().getPrice() * item.getTotalQuantity();
        }

        double shippingFee = 0;
        double vat = totalPrice * 0.10;
        double totalPayment = 0;

        if (vpercent != null) {
            totalPayment = (totalPrice + vat + shippingFee) * ((100 - (double) vpercent.getPercent()) / 100);
            session.setAttribute("vpercent", vpercent.getPercent());
        } else {
            totalPayment = totalPrice + vat + shippingFee;
        }

        request.setAttribute("totalmoney", (int) totalPayment);
        request.getRequestDispatcher("vnpay_pay.jsp").forward(request, response);
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
