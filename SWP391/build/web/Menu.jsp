<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <style>
        .view {
            position: absolute;
            top: 10;
            right: 5;
            z-index: 1;
        }

        .navbar {
            position: relative;
            z-index: 2;
        }

        body {
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        #logreg-forms {
            background: rgba(255, 255, 255, 0.8);
            padding: 15px;
            border-radius: 10px;
        }

        .jumbotron {
            background-image: url('https://trungnguyenlegendcafe.net/wp-content/uploads/2023/11/app-1600x800px-1-scaled.jpg');
            background-size: cover;
            background-position: center;
            padding: 13rem 5rem;
        }

        a {
            color: #856404;
            text-decoration: none;
            background-color: transparent;
        }

        .card-body {
            -webkit-box-flex: 1;
            -ms-flex: 1 1 auto;
            flex: 1 1 auto;
            padding: 1.25rem;
            text-align: center;
        }

        .bg-primary {
            background-color: #6c5309 !important;
        }

        .bg-success {
            background-color: #6c5309 !important;
        }

        .btn-success {
            color: #fff;
            background-color: #6c5309;
            border-color: #6c5309;
        }

        .btn-danger {
            color: #383d41;
            background-color: #fff;
            border-color: black;
        }

        .btn-feedback {
            background-color: transparent;
            color: #ffc107;
            border: none;
            transition: all 0.3s;
            font-weight: bold;
            font-size: 17px;
        }

        .btn-feedback:hover {
            transform: scale(1.05);
            text-decoration: none;
            color: #6c5309;
            background-color: transparent;
            border-color: #fff;
        }
    </style>
    <div class="view ml-auto">
        <a href="ListFeedback" class="btn btn-primary btn-block btn-feedback">
            View Feedback Shop
        </a>
    </div>
    <div class="container">
        <a class="navbar-brand" href="home">The Coffee Cute</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
            <ul class="navbar-nav m-auto">

                <c:if test="${sessionScope.acc.isAdmin == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="listvoucher">Voucher</a>
                    </li>
                    <li class="nav-item">
                        <!--                        <a class="nav-link" href="vieworder">Manager order</a>-->
                        <a class="nav-link" href="https://sandbox.vnpayment.vn/merchantv2/">Manager order</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc.isAdmin == 0 || sessionScope.acc.isSeller == 0}">
                    <li class="nav-item">
                        <a class="nav-link" href="vieworder">View Order History</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc != null && sessionScope.acc.isAdmin != 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="profile">Profile</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc.isAdmin == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="accmanager">Manager Account</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc.isAdmin == 1 || sessionScope.acc.isSeller == 1}">
                    <li class="nav-item">
                        <a class="nav-link" href="manager">Manager Products</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.acc == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="Login.jsp">Login</a>
                    </li>
                </c:if>

            </ul>

            <form action="search" method="post" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input value="${txtSearch}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i>Search</i>
                        </button>
                    </div>
                </div>
            </form>
            <a class="btn btn-success btn-sm ml-3" href="ListCart">
                <i class="fa fa-shopping-cart"></i> Cart
                <!--                            <span class="badge badge-light">0</span>-->
            </a>
        </div>
    </div>
</nav>
<section class="jumbotron text-center">
    <div class="container">
        <h1 class="jumbotron-heading"></h1>
        <p class="lead text-muted mb-0"></p>
    </div>
</section>

<script>
    var images = [
        'https://trungnguyenlegendcafe.net/wp-content/uploads/2023/11/app-1600x800px-1-scaled.jpg',
        'https://file.hstatic.net/1000075078/file/desktop_6d855893790542d6931f213230b2dd57.jpg',
        'https://annicoffee.vn/public/manipulate/0x0/slider-images/1657529785-ca-phe-hat-chat-luong-den-tu-vung-tay-nguyen-anni-coffee.jpg?watermark=0'


    ]; // Danh sách các đường dẫn ảnh

    var currentIndex = 0; // Index của ảnh hiện tại

    function changeImage() {
        var jumbotron = document.querySelector('.jumbotron');
        currentIndex = (currentIndex + 1) % images.length; // Chuyển đến ảnh tiếp theo
        jumbotron.style.backgroundImage = 'url(' + images[currentIndex] + ')';
    }

    setInterval(changeImage, 3000); // Thực hiện chuyển ảnh sau mỗi 3 giây (3000 milliseconds)
</script>
<!--end of menu-->
