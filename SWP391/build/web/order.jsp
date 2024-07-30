<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order History</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="container mt-5">
                <h1 class="text-center mb-4">Order History</h1>
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Total Amount</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orders}" var="o">
                        <tr>
                            <td>${o.orderID}</td>
                            <td>${o.orderDate}</td>
                            <td>${o.totalAmount}</td>
                            <td>${o.status}</td>


                            <td><a href="vieworderdetail?orderId=${o.orderID}">View Details</a></td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
            <a class="btn btn-danger btn-block" href="home">Home</a>

        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    </body>
</html>
