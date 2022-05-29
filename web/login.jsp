<%-- 
    Document   : login
    Created on : Mar 4, 2022, 7:54:53 AM
    Author     : To Quyen Phan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="google-signin-client_id" content="YOUR_CLIENT_ID.apps.googleusercontent.com">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <style>
            body{
                width: 100%;
                background-image: url("images/5fff49a0a8de7700187d3aed.jpg");
                background-repeat: no-repeat;
                background-position: center;
                background-size: cover;
                position: relative;
            }
            .return{
                text-decoration: none;
                color: black;
                position: absolute;
                top: 5%;
                left: 1%;
            }
            form{
                position: absolute;
                width: 70%;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background: rgba(240, 240, 240, 0.5);
                padding: 4rem;
                text-align: center;
            }
            form h1{
                text-transform: uppercase;
            }
            form input{
                width: 50%;
                padding: 1rem;
                margin: 0.5rem auto;
                outline: none;
                text-align: center;
                border: 2px solid #33cc00;
                border-radius: 2rem;
                transition: 0.25s;
            }
            form input:focus{
                width: 60%;
                border-color: black;
            }
            form input[type = "submit"]{
                width: 5rem;
                border: 2px solid #0066ff;
                cursor: pointer;
            }
            form input[type = "reset"]{
                width: 5rem;
                border: 2px solid #ff9933;
                cursor: pointer;
            }
            form input[type = "submit"]:hover{
                background: #0066ff;
                color: white;  
            }
            form input[type = "reset"]:hover{
                background: #ff9933;
                color: white;
            }
            form input[type = "checkbox"]{
                width: 3%;
                margin: 0;
                margin-top: 0.1rem;
                float: left;
            } 
            .g-recaptcha{
                display: inline-block;
                text-align: center;
            }
            h4{
                float: left;
            }
            .forget{
                float: right;
            }
            .fa{
                background: #dd4b39;
                color: white;
                padding: 0.5rem;
                font-size: 1rem;
                text-align: center;
                text-decoration: none;
                border-radius: 100%;
            }
            .error{
                display: block;
                position: absolute;
                width: 20%;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background: white;
                border: 1px solid black;
                border-radius: 2%;
                padding: 4rem;
                text-align: center;
            }
            .error a{
                color: red;
            }
        </style>
        <script src='https://www.google.com/recaptcha/api.js?hl=en'></script>
    </head>
    <body>
        <!-- Gửi lại action GetSite để lấy danh sách product -->
        <a class="return" href="MainController?action=GetSite">Return to website...</a>
        <!-- Gửi userID, password, recaptcha qua MainController bằng method POST--> 
        <form action="MainController" method="POST">
            <h1>Login</h1>
            <input type="text" placeholder="Username" name="userID" required=""/></br>
            <input type="password" placeholder="Password" name="password" required=""/></br>
            <!--Khu xuất hiện recaptcha -->
            <div class="g-recaptcha" data-sitekey="6LelZAsTAAAAAAv1ADYDnq8AzbmPmbMvjh-xhfgB"></div></br>
            <input type="submit" name="action" value="Login"/>
            <input type="reset" value="Reset"/></br>
            <input type="checkbox" checked="checked" name="remember"/><!-- Trang trí -->
            <h4>Remember me</h4> <!--Trang trí -->
            <!--Login with google -->
            <!--Gọi hộp thoại đăng nhập và cài đặt URL chuyển hướng-->
            <span>Login with: <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/VegetableStore/login-google&response_type=code
                                 &client_id=779872057832-jrkut0douckp55hli4pa4ug0cbps4ti2.apps.googleusercontent.com&approval_prompt=force" class="fa fa-google"></a></span>
            <span><a href="#" class="forget">Forget password?</a></span><!--Trang trí-->
        </form>
        <%
            //Hiển thị nếu có lỗi
            String error = (String) request.getAttribute("ERROR");
            if (error != null) {
        %>
        <div class="error" id="error">
            <h3><%= error%></h3>
            </br>
            <!--Nhập lại userID và password-->
            <a href="login.jsp">Try again!</a>
        </div>
        <%
            }
        %>
    </body>
</html>
