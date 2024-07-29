<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login Form</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <style>
            body {
                background-image: url('https://static.vinwonders.com/production/cafe-da-lat-banner.jpg');
                background-size: cover;
                background-repeat: no-repeat;
                background-attachment: fixed;
            }
            #logreg-forms {
                background: rgba(255, 255, 255, 0.8); /* Make form background semi-transparent */
                padding: 15px;
                border-radius: 10px;
            }
            .btn-success {
                color: #fff;
                background-color: #865d1f;
                border-color: #181919;
            }
            .btn-primary {
                background-color: #ffffff;
                border-color: #181919;
            }
        </style>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <div id="logreg-forms">
            <form class="form-signin" action="login" method="post">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center">Sign in</h1>

                <div style="color: blue;">
                    <c:out value="${mess}" />
                </div><br>
                
                    <div style="color: blue;">
                    <c:out value="${message}" />
                </div><br>

                <input name="user"  type="text" id="inputEmail" class="form-control" placeholder="Username" required="" autofocus="">
                <input name="pass"  type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
                <div>
                    <a href="forgotPassword.jsp" >Forgot Password?</a>
                </div>
                <div class="g-recaptcha" data-sitekey="6LdRsvApAAAAAPMbHD0cj_4mwjyU22D1A9Q3OAnf"></div>

                <button class="btn btn-success btn-block" type="submit"><i class="fas fa-sign-in-alt"></i> Sign in</button>
                <hr>
                <a style="color: black;" class="btn btn-primary btn-block" href="Signup.jsp"><i class="fas fa-user-plus"></i> Sign up New Account</a>
            </form>
            <br>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>