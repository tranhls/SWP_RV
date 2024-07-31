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

        <link href="css/style.css" rel="stylesheet" type="text/css"/>

        <style>
            .gallery-wrap .img-big-wrap img {
                height: 450px;
                width: auto;
                display: inline-block;
                cursor: zoom-in;
            }


            .gallery-wrap .img-small-wrap .item-gallery {
                width: 60px;
                height: 60px;
                border: 1px solid #ddd;
                margin: 7px 2px;
                display: inline-block;
                overflow: hidden;
            }

            .gallery-wrap .img-small-wrap {
                text-align: center;
            }
            .gallery-wrap .img-small-wrap img {
                max-width: 100%;
                max-height: 100%;
                object-fit: cover;
                border-radius: 4px;
                cursor: zoom-in;
            }
            .img-big-wrap img{
                width: 100% !important;
                height: auto !important;
            }
        </style>
    </head>
    <body>
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

            <div class="container">
                <div class="row">
                    <div class="col">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="home">Home</a></li>
                                <li class="breadcrumb-item"><a href="category?catID=${detail.catID}">${detail.category}</a></li>
                            <li class="breadcrumb-item active" aria-current="#">${detail.name}</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">

                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                        <ul class="list-group category_block">
                            <c:forEach items="${listC}" var="o">
                                <li class="list-group-item text-white ${checkCat == o.catID ? "active" : ""}"><a href="category?catID=${o.catID}">${o.category}</a></li>
                                <!--                                kiem tra xem nguoi dung nhan vao cat nao thi goi class active de in xanh-->
                            </c:forEach>

                        </ul>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Top products</div>
                        <c:forEach items="${listTop1}" var="o">
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
                    <div class="container">
                        <div class="card">
                            <div class="row">
                                <aside class="col-sm-5 border-right">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <div> <a href="#"><img src="${detail.image}"></a></div>
                                            <dl class="item-property">
                                                <dd><p></p></dd>
                                                <dt>The Coffee Cute</dt>
                                                <dd><p>Chúc quý khách ngon miệng khi dùng thức uống của chúc tôi và kính chúc quý khách có 1 ngày thật tuyệt vời!</p></dd>
                                            </dl>
                                        </div> <!-- slider-product.// -->
                                        <div class="img-small-wrap">
                                        </div> <!-- slider-nav.// -->
                                    </article> <!-- gallery-wrap .end// -->
                                </aside>
                                <aside class="col-sm-7">
                                    <form action="AddToCart" method="GET">
                                        <article class="card-body p-5">
                                            <h3 class="title mb-3">${detail.name}</h3>
                                            <input hidden value="${detail.productID}" name="id">
                                            <p class="price-detail-wrap"> 
                                                <span class="price h3 text-warning"> 
                                                    <span class="currency" style="color: orange;">VND </span><span class="num" style="color: orange;">${detail.price}</span>
                                                </span> 
                                            </p> <!-- price-detail-wrap .// -->
                                            <dl class="item-property">
                                                <dt>Category - Description</dt>
                                                <dd><p>
                                                        ${detail.category} - ${detail.description}
                                                    </p></dd>
                                            </dl>

                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <dl class="param param-inline">
                                                        <dt>Quantity: </dt>
                                                        <dd>
                                                            <select class="form-control form-control-sm" name="quantity" style="width:70px;">
                                                                <option> 1 </option>
                                                                <option> 2 </option>
                                                                <option> 3 </option>
                                                                <option> 4 </option>
                                                                <option> 5 </option>
                                                                <option> 6 </option>
                                                                <option> 7 </option>
                                                                <option> 8 </option>
                                                                <option> 9 </option>
                                                                <option> 10 </option>
                                                                <option> 11 </option>
                                                                <option> 12 </option>
                                                                <option> 13 </option>
                                                                <option> 14 </option>
                                                                <option> 15 </option>
                                                            </select>
                                                        </dd>
                                                    </dl>  <!-- item-property .// -->
                                                </div> <!-- col.// -->

                                            </div> <!-- row.// -->
                                            <hr>
                                            <a href="#" class="btn btn-lg btn-primary text-uppercase"> Buy now </a>
                                            <button type="submit" class="btn btn-lg btn-outline-primary text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </button>
                                        </article>
                                    </form><!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->


                    </div>
                </div>
            </div>
        </div>
        <script>
            function handleFormSubmit(event) {
                event.preventDefault();  // Prevent the default form submission

// Trigger the toast
                $('.toast').toast({delay: 3000});
                $('.toast').toast('show');

// Submit the form after the toast has been displayed for 3 seconds
                setTimeout(function () {
                    event.target.submit();
                }, 3000);  // Submit the form after 3 seconds
            }

// Attach the handler to the form
            document.addEventListener('DOMContentLoaded', function () {
                const form = document.querySelector('form[action="AddToCart"]');
                form.addEventListener('submit', handleFormSubmit);
            });
        </script>

        <jsp:include page="Footer.jsp"></jsp:include>
    </body>
</html>
