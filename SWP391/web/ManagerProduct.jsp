<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap CRUD Data Table for Database with Modal Form</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img {
                width: 200px;
                height: 120px;
            }
            .table-title {
                padding-bottom: 15px;
                background: #7c8896;
            }
            a {
                color: #ffffff;
            }
            body {
                background-color: #fff;
            }
            .error {
                color: red;
                font-size: 12px;
            }
        </style>
    </head>
    <body>
        <c:if test="${mess != null}" >
            ${mess}
        </c:if>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <a href="home"><h2>Manage <b>Product</b></h2></a>
                        </div>
                        <div class="col-sm-6">
                            <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                            <button onclick="confirmDelete()" class="btn btn-danger"><i class="material-icons">&#xE15C;</i> <span>Delete</span></button>
                            <a href="#addCategoryModal" class="btn btn-primary" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Category</span></a>
                            <a href="managerProduct" class="btn btn-success">Excel</a>

                        </div>
                    </div>
                </div>
                <!-- Display success message if present -->
                <% if (request.getAttribute("success") != null) { %>
                <div class="alert alert-success" role="alert">
                    <%= request.getAttribute("success") %>
                </div>
                <% } %>

                <!-- Display error message if present -->
                <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= request.getAttribute("error") %>
                </div>
                <% } %>
                <form id="deleteForm" action="delete" method="post">
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="selectAll" onclick="toggleSelectAll(this)">
                                        <label for="selectAll"></label>
                                    </span>
                                </th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Category</th>
                                <th>Image</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listP}" var="o">
                                <tr>
                                    <td>
                                        <span class="custom-checkbox">
                                            <input type="checkbox" id="checkbox${o.productID}" name="pid" value="${o.productID}">
                                            <label for="checkbox${o.productID}"></label>
                                        </span>
                                    </td>
                                    <td>${o.productID}</td>
                                    <td>${o.name}</td>
                                    <td>${o.description}</td>
                                    <td>${o.price}</td>
                                    <td>${o.category}</td>
                                    <td>
                                        <img src="${o.image}">
                                    </td>
                                    <td>
                                        <a href="loadProduct?pid=${o.productID}" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="javascript:void(0);" onclick="confirmDeleteSingle('${o.productID}')" class="delete" data-toggle="modal">
                                            <i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>

        <script>
            function confirmDelete() {
                if (confirm("Are you sure you want to delete these products?")) {
                    document.getElementById("deleteForm").submit();
                }
            }

            function confirmDeleteSingle(productId) {
                if (confirm("Are you sure you want to delete this product?")) {
                    var form = document.getElementById("deleteForm");
                    var input = document.createElement("input");
                    input.type = "hidden";
                    input.name = "pid";
                    input.value = productId;
                    form.appendChild(input);
                    form.submit();
                }
            }

            function toggleSelectAll(source) {
                checkboxes = document.getElementsByName('pid');
                for (var i = 0; i < checkboxes.length; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }
            function validateForm() {
                var isNameValid = validateName('name', 'name-error');
                var isDescriptionValid = validateDescription('description', 'description-error');
                var isPriceValid = validatePrice('price', 'price-error');
                var isImageValid = validateImage('image', 'image-error');
                var isCatIDValid = validateCatID('catID', 'catID-error');
                var isCategoryValid = validateCategory('Category', 'Category-error');

                return isNameValid && isDescriptionValid && isPriceValid && isImageValid && isCatIDValid && isCategoryValid;
            }

            function validateName(nameId, nameErrorId) {
                var name = document.getElementById(nameId).value;
                var errorElement = document.getElementById(nameErrorId);
                if (name.trim() === "") {
                    errorElement.textContent = "Name cannot be empty.";
                    errorElement.style.display = "block";
                    return false;
                } else {
                    errorElement.style.display = "none";
                    return true;
                }
            }
            function validateDescription(descriptionId, descriptionErrorId) {
                var description = document.getElementById(descriptionId).value;
                var errorElement = document.getElementById(descriptionErrorId);
                if (description.trim().length < 5 || description.trim().length > 500) {
                    errorElement.textContent = "Description must be between 5 and 500 characters.";
                    errorElement.style.display = "block";
                    return false;
                } else {
                    errorElement.style.display = "none";
                    return true;
                }
            }
            function validatePrice(priceId, priceErrorId) {
                var price = document.getElementById(priceId).value;
                var errorElement = document.getElementById(priceErrorId);
                if (isNaN(price) || price < 1) {
                    errorElement.textContent = "Price must be greater than or equal to 1.";
                    errorElement.style.display = "block";
                    return false;
                } else {
                    errorElement.style.display = "none";
                    return true;
                }
            }

            function validateImage(imageId, imageErrorId) {
                var image = document.getElementById(imageId).value;
                var errorElement = document.getElementById(imageErrorId);
                if (image.trim() === "") {
                    errorElement.textContent = "Image URL cannot be empty.";
                    errorElement.style.display = "block";
                    return false;
                } else {
                    errorElement.style.display = "none";
                    return true;
                }
            }

//            function validateCatID(catIDId, catIDErrorId) {
//                var catID = document.getElementById(catIDId).value;
//                var errorElement = document.getElementById(catIDErrorId);
//                if (isNaN(catID) || !Number.isInteger(parseFloat(catID))) {
//                    errorElement.textContent = "CatID must be an integer.";
//                    errorElement.style.display = "block";
//                    return false;
//                } else {
//                    errorElement.style.display = "none";
//                    return true;
//                }
//            }
//            function validateCategory(categoryId, categoryErrorId) {
//                var category = document.getElementById(categoryId).value;
//                var errorElement = document.getElementById(categoryErrorId);
//                if (category.trim() === "") {
//                    errorElement.textContent = "Category cannot be empty.";
//                    errorElement.style.display = "block";
//                    return false;
//                } else {
//                    errorElement.style.display = "none";
//                    return true;
//                }
//            }

        </script>
        <script>
            function confirmAddCategory() {
                return confirm("When adding a new Category, it will be permanent and cannot be easily deleted. Are you sure you want to add a Category?");
            }
        </script>


        <!-- Add Modal HTML -->
        <div id="addEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="add" method="post">
                        <div class="modal-header">
                            <h4 class="modal-title">Add Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" id="name" type="text" class="form-control" required onblur="validateName('name', 'name-error');">
                                <span id="name-error" class="error" style="display: none;"></span>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <textarea name="description" id="description" class="form-control" required onblur="validateDescription('description', 'description-error');"></textarea>
                                <span id="description-error" class="error" style="display: none;"></span>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" id="price" type="number" class="form-control" required onblur="validatePrice('price', 'price-error');">
                                <span id="price-error" class="error" style="display: none;"></span>
                            </div>
                            <div class="form-group">
                                <label>Image</label>
                                <input name="image" id="image" type="text" class="form-control" required onblur="validateImage('image', 'image-error');">
                                <span id="image-error" class="error" style="display: none;"></span>

                            </div>
                            <!--                            <div class="form-group">
                                                            <label>CatID</label>
                                                            <input name="catID" id="catID" type="number" class="form-control" value="2">
                                                            <span id="catID-error" class="error" style="display: none;"></span>
                                                        </div>-->
                            <div class="form-group">
                                <label>Category</label>
                                <select name="catName" class="form-control">
                                    <c:forEach items="${listC}" var="o">
                                        <option value="${o.category}">${o.category}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->
        <div id="editEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">
                            <h4 class="modal-title">Edit Employee</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Address</label>
                                <textarea class="form-control" required></textarea>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Add Category Modal HTML -->
        <div id="addCategoryModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="AddCategoryServlet" method="post" onsubmit="return confirmAddCategory()">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Add New Category</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="categoryName">Category Name:</label>
                                <input type="text" class="form-control" id="categoryName" name="categoryName" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-success">Add Category</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </body>
</html>
