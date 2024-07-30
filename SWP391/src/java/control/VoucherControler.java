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
public class VoucherControler extends HttpServlet {

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

        String code = request.getParameter("code");
        DAO dao = new DAO();
        voucher vpercent = dao.getPercent(code);

        if (vpercent != null) {
            HttpSession session = request.getSession();
            session.setAttribute("vpercentView", vpercent.getPercent());
            session.setAttribute("code", code);

            DAO d = new DAO();
            Account account = (Account) session.getAttribute("acc");
            List<Cart> carts = d.listCart(account.getAccountID());

            double totalPrice = 0;
            for (Cart item : carts) {
                totalPrice += item.getProduct().getPrice() * item.getTotalQuantity();
            }

            double shippingFee = 0;
            double vat = totalPrice * 0.10;
            double totalPayment = (totalPrice + vat + shippingFee) * ((100 - (double) vpercent.getPercent()) / 100);

            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("shippingFee", shippingFee);
            request.setAttribute("vat", vat);
            request.setAttribute("totalPayment", totalPayment);

            request.setAttribute("list", carts);

            request.getRequestDispatcher("Cart.jsp").forward(request, response);
        } else {
            // Xử lý khi không tìm thấy voucher với mã code tương ứng
            response.getWriter().print("Voucher not found");
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
