<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Error Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .error-container {
                text-align: center;
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            h2 {
                color: #f44336;
            }
            p {
                font-size: 18px;
            }
                     
        </style>
    </head>
    <body>
        <div class="error-container">
            <h2>Access Denied</h2>
            <p>${message}</p>
            <p><a href="home">Back to Home</a></p>
        </div>
    </body>
</html>
