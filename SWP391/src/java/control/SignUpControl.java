package control;

import dao.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.passwordEncryption;

public class SignUpControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("fullname");
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String confirmPass = request.getParameter("confirm-password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

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
        } 
//        else {
//            pass = passwordEncryption.toSHA1(pass);
//        }

        if (hasError) {
            request.setAttribute("errorMessage", errorMessage.toString());
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
        } else {
            try {
                dao.signUp(user, pass);
                dao.signUpUser(name, email, phone, address);
                request.getRequestDispatcher("Success.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Có lỗi xảy ra trong quá trình đăng ký. Vui lòng thử lại sau.");
                request.getRequestDispatcher("Signup.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
