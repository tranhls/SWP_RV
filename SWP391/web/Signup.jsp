<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Form Đăng Ký</title>
        <!-- Liên kết tệp CSS -->
        <link href="css/style_1.css" rel="stylesheet" type="text/css"/>
        <script>
            function validateForm() {
                var password = document.getElementById("password").value;
                var confirmPassword = document.getElementById("confirm-password").value;
                if (password !== confirmPassword) {
                    alert("Mật khẩu xác nhận không khớp!");
                    return false;
                }
                return true;
            }

            function validateInput(inputId, errorMessageId, minLength, errorMessage) {
                var inputValue = document.getElementById(inputId).value;
                if (inputValue.length < minLength) {
                    document.getElementById(errorMessageId).style.display = "block";
                    document.getElementById(errorMessageId).innerText = errorMessage;
                    return false;
                } else {
                    document.getElementById(errorMessageId).style.display = "none";
                    return true;
                }
            }

            function validateEmail(emailId, emailErrorId) {
                var email = document.getElementById(emailId).value;
                var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                if (!emailPattern.test(email)) {
                    document.getElementById(emailErrorId).style.display = "block";
                    document.getElementById(emailErrorId).innerText = "Email không hợp lệ";
                    return false;
                } else {
                    document.getElementById(emailErrorId).style.display = "none";
                    return true;
                }
            }

            function validatePhone(phoneId, phoneErrorId) {
                var phone = document.getElementById(phoneId).value;
                var phonePattern = /^\d{10}$/;
                if (!phonePattern.test(phone)) {
                    document.getElementById(phoneErrorId).style.display = "block";
                    document.getElementById(phoneErrorId).innerText = "Số điện thoại phải có 10 chữ số";
                    return false;
                } else {
                    document.getElementById(phoneErrorId).style.display = "none";
                    return true;
                }
            }


            function validateAddress(addressId, addressErrorId) {
                var address = document.getElementById(addressId).value;
                if (address.length < 10) {
                    document.getElementById(addressErrorId).style.display = "block";
                    document.getElementById(addressErrorId).innerText = "Địa chỉ phải có ít nhất 10 ký tự";
                    return false;
                } else {
                    document.getElementById(addressErrorId).style.display = "none";
                    return true;
                }
            }
        </script>
    </head>
    <body>
        <div class="registration-form">
            <h2>Đăng Ký Tài Khoản</h2>
            <% 
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null && !errorMessage.isEmpty()) {
            %>
            <div class="error-message">
                <%= errorMessage %>
            </div>
            <% } %>
            <form action="signup" method="POST" onsubmit="return validateForm();">
                <!-- Sắp xếp các trường thông tin theo hàng ngang -->
                <div class="form-group">
                    <label for="fullname">Họ và Tên</label>
                    <input type="text" id="fullname" name="fullname" required autocomplete="name" onblur="validateInput('fullname', 'fullname-error', 6, 'Tên người dùng phải có ít nhất 6 ký tự.')">
                    <span id="fullname-error" class="error" style="display: none;"></span>
                </div>
                <div class="form-group">
                    <label for="username">Tên Đăng Nhập</label>
                    <input type="text" id="username" name="username" required autocomplete="username" onblur="validateInput('username', 'username-error', 6, 'Tên đăng nhập phải có ít nhất 6 ký tự.')">
                    <span id="username-error" class="error" style="display: none;"></span>
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input type="password" id="password" name="password" required autocomplete="new-password">
                </div>
                <div class="form-group">
                    <label for="confirm-password">Xác Nhận Mật khẩu</label>
                    <input type="password" id="confirm-password" name="confirm-password" required autocomplete="new-password">
                    <span id="confirm-password-error" class="error" style="display: none;"></span>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required autocomplete="email" onblur="validateEmail('email', 'email-error')">
                    <span id="email-error" class="error" style="display: none;"></span>
                </div>
                <div class="form-group">
                    <label for="phone">Số Điện Thoại</label>
                    <input type="text" id="phone" name="phone" required autocomplete="tel" onblur="validatePhone('phone', 'phone-error')">
                    <span id="phone-error" class="error" style="display: none;"></span>
                </div>
                <div class="form-group">
                    <label for="address">Địa Chỉ</label>
                    <input type="text" id="address" name="address" required autocomplete="street-address" onblur="validateAddress('address', 'address-error')">
                    <span id="address-error" class="error" style="display: none;"></span>
                </div>
                <button type="submit">Đăng Ký</button>
            </form>
        </div>
    </body>
</html>
