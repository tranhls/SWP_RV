package control;

import dao.DAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.passwordEncryption;

/**
 * author: LamHP
 */
@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        String email = (String) session.getAttribute("email");
        RequestDispatcher dispatcher = null;
        boolean hasError = false;
        StringBuilder errorMessage = new StringBuilder();

        // Kiểm tra null và khớp mật khẩu
        if (newPassword == null || newPassword.isEmpty() || confPassword == null || confPassword.isEmpty() || !newPassword.equals(confPassword)) {
            request.setAttribute("status", "resetFailed");
            request.setAttribute("message", "Mật khẩu mới và mật khẩu khác nhận không khớp nhau hoặc bị bỏ trống! ");
            dispatcher = request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Kiểm tra điều kiện password phải có 8 kí tự
        if (newPassword.length() < 8) {
            request.setAttribute("status", "resetFailed");
            request.setAttribute("message", "Mật khẩu phải có ít nhất 8 kí tự ");
            dispatcher = request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);
            return;
        } 
        else {
            newPassword = passwordEncryption.toSHA1(newPassword);
        }

        try {

            DAO dao = new DAO();
            if (dao.updatePassword(email, newPassword)) {
                request.setAttribute("message", "Đổi mật khẩu thành công");
                dispatcher = request.getRequestDispatcher("newPassword.jsp");

            } else {
                request.setAttribute("message", "Đổi mật khẩu thất bại");
                dispatcher = request.getRequestDispatcher("newPassword.jsp");
            }
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Thay đổi mật khẩu thất bại");
            dispatcher = request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);

        }
    }
}
