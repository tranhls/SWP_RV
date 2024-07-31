<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Customer Feedbacks</title>
        <!-- Include Bootstrap CSS -->
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- Include Font Awesome CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <!-- Custom CSS for Star Ratings -->
        <!-- Bieu tuong sao -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
            }
            .container {
                margin-top: 50px;
            }
            .table {
                background-color: #fff;
                border-collapse: separate;
                border-spacing: 0;
                width: 100%;
                border: 1px solid #dee2e6;
                border-radius: 10px;
                overflow: hidden;
                box-shadow: 0 0 20px rgba(0,0,0,0.1);
            }
            .table th, .table td {
                padding: 12px 15px;
                vertical-align: middle;
                border-top: 1px solid #dee2e6;
            }
            .table th {
                background-color: rgb(95,99,104);
                color: #fff;
                border-top: none;
                text-align: center;
            }
            .table tbody tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            .star-rating {
                color: #ffc107;
            }
            .star-rating .fa-star {
                font-size: 18px;
            }
            .star-rating .inactive {
                color: #e3e4e5;
            }
            /* Animation for the title */
            @keyframes titleAnimation {
                0% {
                    transform: scale(1);
                    opacity: 0;
                }
                50% {
                    transform: scale(1.1);
                    opacity: 0.5;
                }
                100% {
                    transform: scale(1);
                    opacity: 1;
                }
            }
            .title {
                animation: titleAnimation 2s ease-in-out;
            }
            .back-button {
                margin-bottom: 20px;
                display: flex;
                justify-content: center;
            }
            .back-button a {
                text-decoration: none;
                background-color: transparent;
                border: none;
                color: #007bff;
                font-size: 1em;
                padding: 5px 10px;
                border-radius: 5px;
            }
            .back-button a:hover {
                color: #0056b3;
                background-color: transparent;
                border: none;
            }
            .back-button a:focus, .back-button a:active {
                outline: none;
                box-shadow: none;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h2 class="text-center mb-4 title">Customer Feedbacks</h2>
            <div class="back-button">
                <a href="home" class="btn btn-primary btn-sm">
                    <i class="fa fa-home"></i> Home
                </a>
            </div>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Customer Name</th>
                            <th>Review Rating</th>
                            <th>Comments</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="feedback" items="${feedbacks}" varStatus="status">
                            <tr>
                                <td>${customerNames[status.index]}</td>
                                <td class="text-center">
                                    <div class="star-rating">
                                        <c:forEach begin="1" end="5" var="star">
                                            <i class="fa fa-star ${star <= feedback.rating ? '' : 'inactive'}"></i>
                                        </c:forEach>
                                    </div>
                                </td>
                                <td>${feedback.comments}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
