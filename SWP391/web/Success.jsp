<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Tạo tài khoản thành công</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                text-align: center;
                padding: 50px;
            }
            .container {
                background-color: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                display: inline-block;
            }
            h1 {
                color: #4CAF50;
            }
            p {
                font-size: 1.2em;
            }
            a {
                color: #4CAF50;
                text-decoration: none;
                border: 1px solid #4CAF50;
                padding: 10px 20px;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
            a:hover {
                background-color: rgba(255, 255, 255, 0.8);
                color: #fff;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Tài khoản của bạn đã được tạo thành công!</h1>
            <p>Cảm ơn bạn đã đăng ký. Bạn có thể đăng nhập bằng thông tin tài khoản của mình.</p>
            <a href="Login.jsp">Đăng nhập</a>
        </div>
    </body>
</html>
