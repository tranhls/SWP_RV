<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Profile Settings</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-image: url('https://png.pngtree.com/background/20230412/original/pngtree-coffee-culture-cartoon-border-background-picture-image_2393730.jpg');
                background-size: cover;
                background-repeat: no-repeat;
                background-attachment: fixed;
            }

            .form-control:focus {
                box-shadow: none;
                border-color: #BA68C8;
            }

            .profile-button {
                background: rgb(99, 39, 120);
                box-shadow: none;
                border: none;
            }

            .profile-button:hover {
                background: #682773;
            }

            .profile-button:focus {
                background: #682773;
                box-shadow: none;
            }

            .profile-button:active {
                background: #682773;
                box-shadow: none;
            }

            .back:hover {
                color: #682773;
                cursor: pointer;
            }

            .labels {
                font-size: 11px;
            }

            .add-experience:hover {
                background: #BA68C8;
                color: #fff;
                cursor: pointer;
                border: solid 1px #BA68C8;
            }
        </style>
    </head>
    <form action="profile" method="POST">
        <body>
            <div class="container rounded bg-white mt-5 mb-5">
                <div class="row">
                    <div class="col-md-3 border-right">
                        <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                            <a class="btn btn-danger btn-block" href="home">Home</a>
                            <img class="rounded-circle mt-5" width="150px" src="https://masterbundles.com/wp-content/uploads/2023/03/preview-143.jpg">
                            <span class="font-weight-bold">The Coffee Cute</span>
                            <span> </span>
                        </div>
                    </div>
                    <div class="col-md-5 border-right">
                        <div class="p-3 py-5">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h4 class="text-right">Edit personal information</h4>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <label class="labels">Name</label>
                                    <input name="name" type="text" class="form-control" placeholder="Enter Name" value="${cus.name}">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Email</label>
                                    <input name="email" type="text" class="form-control" placeholder="Enter Email" value="${cus.email}">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Phone</label>
                                    <input name="phone" type="text" class="form-control" placeholder="Enter Phone" value="${cus.phone}">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Address</label>
                                    <input name="address" type="text" class="form-control" placeholder="Enter Address" value="${cus.address}">
                                </div>
                            </div>
                            <div class="mt-5 text-center">
                                <button class="btn btn-primary profile-button" type="submit">Save Profile</button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="p-3 py-5">
                            <div class="d-flex justify-content-between align-items-center experience">
                                <h4 class="text-right">Edit login information</h4>
                            </div>
                            <br>
                            <div class="col-md-12">
                                <label class="labels">Account ID</label>
                                <input name="id" type="text" class="form-control" value="${acc.accountID}" readonly>
                            </div>
                            <br>
                            <div class="col-md-12">
                                <label class="labels">User Login</label>
                                <input name="user" type="text" class="form-control" placeholder="User Login" value="${acc.user}" readonly>
                            </div>
                            <br>
                            <div class="col-md-12">
                                <label class="labels">Password</label>
                                <input name="pass" type="text" class="form-control" placeholder="Password" value="${acc.pass}">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        </body>
    </form>
</html>
