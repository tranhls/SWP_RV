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
                <h1 class="text-center mb-4">Order Detail</h1>
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <td><strong>ProductName</strong></td>
                            <td class="text-center"><strong>Price</strong></td>
                            <td class="text-center"><strong>Quantity</strong></td>


                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${orderDetailses}" var="o">
                        <tr>
                            <td>${o.pname}</td>
                            <td class="text-center">${o.price} VND</td>
                            <td class="text-center">${o.quantity}</td>


                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a class="btn btn-danger btn-block" href="home">Home</a>
            <!--<a href="vieworder"><button>See Orders</button></a>-->  
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    </body>
</html>
