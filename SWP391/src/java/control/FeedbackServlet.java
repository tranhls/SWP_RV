/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import entity.Account;
import entity.Feedback;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author DO NHAT TRUNG ANH
 */
public class FeedbackServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
            out.println("<title>Servlet FeedbackServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackServlet at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        // Lấy dữ liệu từ form
        String customerIDStr = request.getParameter("customerID");
        String orderIDStr = request.getParameter("orderID");
        int customerID = acc.getAccountID();
        int orderID = orderIDStr != null && !orderIDStr.isEmpty() ? Integer.parseInt(orderIDStr) : 0;
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comments = request.getParameter("comments");

        // Tạo đối tượng Feedback từ dữ liệu form
        Feedback feedback = new Feedback(customerID, orderID, rating, comments);

        // Gọi DAO để lưu feedback vào cơ sở dữ liệu
        DAO dao = new DAO();
        boolean success = false;
        try {
            success = dao.addFeedback(feedback);

            if (success) {
                request.setAttribute("feedbackMessage", "Feedback submitted successfully!");
            } else {
                request.setAttribute("feedbackMessage", "Failed to submit feedback!");
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug
            request.setAttribute("feedbackMessage", "Failed to submit feedback!");
        }

        // Forward hoặc redirect về trang JSP để hiển thị thông báo
        request.getRequestDispatcher("home").forward(request, response);

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
