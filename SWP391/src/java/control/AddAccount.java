/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package control;

import dao.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@WebServlet(name = "AddAccount", urlPatterns = {"/addAccount"})
public class AddAccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("fullname");
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String confirmPass = request.getParameter("confirm-password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int role = Integer.parseInt(request.getParameter("role"));

        DAO dao = new DAO();
        boolean hasError = false;
        StringBuilder errorMessage = new StringBuilder();

        // Kiểm tra các điều kiện đầu vào
        if (name.length() < 6) {
            hasError = true;
            errorMessage.append("Tên người dùng phải có ít nhất 6 ký tự");
        }

        if (dao.checkAccExist(user) != null) {
            hasError = true;
            errorMessage.append("Tài khoản đã tồn tại.<br>");
        }

        if (user.length() < 6 || !user.matches("[a-zA-Z0-9]+")) {
            hasError = true;
            errorMessage.append("Tên đăng nhập phải có ít nhất 6 ký tự, chỉ chứa chữ cái và số không cách.<br>");
        }

        if (pass.length() < 8) {
            hasError = true;
            errorMessage.append("Mật khẩu phải có ít nhất 8 ký tự.<br>");
        }

        if (dao.checkEmailExist(email) != null) {
            hasError = true;
            errorMessage.append("Email đã tồn tại.<br>");
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            hasError = true;
            errorMessage.append("Email không hợp lệ.<br>");
        }

        if (!phone.matches("\\d{10}")) {
            hasError = true;
            errorMessage.append("Số điện thoại phải có 10 chữ số.<br>");
        }

        if (address.length() < 10) {
            hasError = true;
            errorMessage.append("Địa chỉ phải có ít nhất 10 ký tự.<br>");
        }

        if (!pass.equals(confirmPass)) {
            hasError = true;
            errorMessage.append("Mật khẩu xác nhận không khớp.<br>");
        }//else {
        //pass = passwordEncryption.toSHA1(pass);
        //}

        if (hasError) {
            request.setAttribute("errorMessage", errorMessage.toString());
            request.getRequestDispatcher("AddAccount.jsp").forward(request, response);

        } else {
            try {
                dao.AddAcc(user, pass, role);
                dao.signUpUser(name, email, phone, address);
                response.sendRedirect("accmanager");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Có lỗi xảy ra trong quá trình đăng ký. Vui lòng thử lại sau.");
                request.getRequestDispatcher("AddAccount.jsp").forward(request, response);
            }
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
