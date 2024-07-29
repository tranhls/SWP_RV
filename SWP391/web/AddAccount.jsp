<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/style_2.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                background-image: url('https://st.quantrimang.com/photos/image/2019/05/03/Hinhnen-Coffee-4.jpg');
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center center;
            }
            .registration-form {
                background-color: rgba(255, 255, 255, 0.8);
                padding: 20px;
                border-radius: 10px;
                max-width: 500px;
                margin: auto;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                font-weight: bold;
            }
            .form-group input, .form-group select {
                width: 100%;
                padding: 8px;
                box-sizing: border-box;
            }
            .error-message {
                color: red;
                margin-bottom: 10px;
            }
            .error {
                color: red;
                display: none;
            }
            button {
                padding: 10px 15px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            button:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div class="registration-form">
            <h2>Add New Account</h2>
            <% 
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null && !errorMessage.isEmpty()) {
            %>
            <div class="error-message">
                <%= errorMessage %>
            </div>
            <% } %>
            <form action="addAccount" method="POST" onsubmit="return validateForm();">
                <!-- Sắp xếp các trường thông tin theo hàng ngang -->
                <div class="form-group">
                    <label for="fullname">Họ và Tên</label>
                    <input type="text" id="fullname" name="fullname" required autocomplete="name">
                    <span id="fullname-error" class="error" style="display: none;"></span>
                </div>
                <div class="form-group">
                    <label for="username">Tên Đăng Nhập</label>
                    <input type="text" id="username" name="username" required autocomplete="username">
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
                    <input type="email" id="email" name="email" required autocomplete="email">
                    <span id="email-error" class="error" style="display: none;"></span>
                </div>
                <div class="form-group">
                    <label for="phone">Số Điện Thoại</label>
                    <input type="text" id="phone" name="phone" required autocomplete="tel">
                    <span id="phone-error" class="error" style="display: none;"></span>
                </div>
                <div class="form-group">
                    <label for="address">Địa Chỉ</label>
                    <input type="text" id="address" name="address" required autocomplete="street-address">
                    <span id="address-error" class="error" style="display: none;"></span>
                </div>
                <div class="form-group">
                    <label>Vai Trò</label>
                    <select name="role">
                        <option value="0">User</option>
                        <option value="1">Seller</option>
                    </select>
                </div>
                <button type="submit">Submit</button>
            </form>
        </div>
    </body>