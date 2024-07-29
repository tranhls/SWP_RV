<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            /* Basic styling for pagination links */
            .pagination {
                display: flex;
                justify-content: center;
                padding: 20px 0;
            }
            .pagination a {
                display: flex;
                align-items: center;
                justify-content: center;
                width: 40px;  /* Set width */
                height: 40px; /* Set height */
                text-decoration: none;
                color: #007BFF;
                border: 1px solid #007BFF;
                margin: 0 5px;
                transition: background-color 0.3s, color 0.3s;
            }
            .pagination a:hover {
                background-color: #007BFF;
                color: #fff;
            }
            .pagination a.active {
                background-color: #007BFF;
                color: #fff;
                border-color: #007BFF;
            }
            .breadcrumb-item.active a {
                color: blue;
            }
            .breadcrumb-container {
                display: flex;
                align-items: center;
            }
            .breadcrumb {
                margin-bottom: 0;
                background: none;
                padding: 0;
                display: flex;
                align-items: center;
            }
            .category_block {
                list-style: none;
                display: flex;
                padding-left: 0;
                margin-bottom: 0;
            }
            .category_block li {
                border: none;
                margin-left: 10px;
            }
            .category_block li a {
                color: #007bff; /* Bootstrap default link color */
            }
            .category_block li.active a {
                color: #0056b3; /* Bootstrap darker link color */
                font-weight: bold;
            }
            body {
                margin: 0;
                font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
                font-size: 1rem;
                font-weight: 700;
                line-height: 3.5;
                color: #212529;
                /* text-align: center; */
                background-color: #fff;
            }
        </style>
    </head>
    <body>
        <!--begin of menu-->
        <jsp:include page="Menu.jsp"></jsp:include>
            <div aria-live="polite" aria-atomic="true" style="position: fixed; top: 20px; right: 20px; z-index: 1050;">
                <div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                    <div class="toast-header">
                        <strong class="mr-auto">Notification</strong>
                        <small>Just now</small>
                        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="toast-body">
                        Item added to cart successfully!
                    </div>
                </div>
            </div>

            <!--end of menu-->
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="breadcrumb-container">
                            <nav aria-label="breadcrumb">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="home">Home</a></li>
                                    <c:forEach items="${listC}" var="o">
                                    <li class="breadcrumb-item ${checkCat == o.catID ? 'active' : ''}">
                                        <a href="category?catID=${o.catID}">${o.category}</a>
                                    </li>
                                </c:forEach>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Top 3 products</div>
                        <c:forEach items="${listTop}" var="o">
                            <div class="card-body">
                                <img class="img-fluid" src="${o.image}" />
                                <h5 class="card-title"><a href="detail?pid=${o.productID}" title="View Product">${o.name}</a></h5>
                                <p class="card-text">Category: ${o.category}</p>
                                <p class="btn btn-danger btn-block">${o.price} VND</p>
                                <hr/>
                            </div>
                        </c:forEach>
                    </div>
                </div>


                <div class="col-sm-9">
                    <div class="row">
                        <c:forEach items="${listP}" var="o">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="${o.image}" alt="Card image cap">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${o.productID}" title="View Product">${o.name}</a></h4>
                                        <p class="card-text show_txt">${o.category}</p>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">${o.price} VND</p>
                                            </div>
                                            <div class="col">
                                                <c:if test="${sessionScope.acc == null}">
                                                    <a href="Login.jsp" class="btn btn-success btn-block">Add to cart</a>
                                                </c:if>

                                                <c:if test="${sessionScope.acc != null}">
                                                    <a href="AddToCart?id=${o.productID}&quantity=1" class="btn btn-success btn-block" onclick="showToast(event)">Add to cart</a>
                                                </c:if>
                                                <script>
                                                    function showToast(event) {
                                                        event.preventDefault();  // Prevent the default action

                                                        // Trigger the toast
                                                        $('.toast').toast({delay: 3000});
                                                        $('.toast').toast('show');

                                                        // After showing the toast, redirect to the AddToCart URL
                                                        const url = event.currentTarget.getAttribute('href');
                                                        setTimeout(function () {
                                                            window.location.href = url;
                                                        }, 500);  // Delay the redirection a bit to let the toast show
                                                    }
                                                </script>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>

            </div>
        </div>

    <center>
        <div class="pagination">
            <c:forEach begin="1" end="${endPage}" var="i">
                <a href="home?index=${i}">${i}</a><!-- truyen index = 1 2 3 -->
            </c:forEach> 
        </div>  
    </center>





    <!-- Footer -->
    <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
