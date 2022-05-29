<%-- 
    Document   : create
    Created on : Mar 5, 2022, 10:31:19 PM
    Author     : To Quyen Phan
--%>

<%@page import="quyen.vegetablestore.users.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up Page</title>
        <link rel="stylesheet" href="css/style.css"/> <!--CSS dùng chung -->
        <style>
            body{
                width: 100%;
                background-image: url("images/freshvegetablesAlexRaths-4c1ea186a88e4fd7b95283eee614600c.jpg");
                background-repeat: no-repeat;
                background-position: center;
                background-size: cover;
                position: relative;
                font-weight: bold;
            }
            .sign-up{
                width: 50%;
                height: 100vh;
                background: rgba(240, 240, 240, 0.5);
                padding: 2rem 4rem;
            }
            form h1{
                text-transform: uppercase;
                text-align: center;
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
                font-size: 1rem;
            }
            form input[type = "reset"]{
                width: 5rem;
                border: 2px solid #ff9933;
                cursor: pointer;
                font-size: 1rem;
            }
            form input[type = "submit"]:hover{
                background: #0066ff;
                color: white; 
            }
            form input[type = "reset"]:hover{
                background: #ff9933;
                color: white;
            }
            a{
                width: 50%;
                padding: 1rem;
                margin: 0.5rem auto;
                outline: none;
                text-align: center;
                border: 2px solid red;
                border-radius: 2rem;
                transition: 0.25s;
                text-decoration: none;
                background: white;
                color: black;
                font-weight: normal;
               
            }
            a:hover{
                background: red;
                color: white;
            }
        </style>
    </head>
    <body>
        <%
            //Lấy ra các lỗi của người dùng cho việc hiển thị lỗi
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if(userError == null){
                userError = new UserError();
            }
        %>
        <div class="sign-up">
            <h1>Sign Up Now</h1>
            <h3>Please fill user's information in the form below:</h3>
            <form action="MainController" method="POST">
                User ID: <input type="text" name="userID" placeholder="Username" required=""/>
                <%= userError.getUserIDError() %></br>
                Full Name: <input type="text" name="fullName" placeholder="Full Name" required=""/>
                <%= userError.getFullNameError() %></br>
                Password: <input type="password" name="password" placeholder="Password"  required=""/></br>
                Confirm: <input type="password" name="confirm" placeholder="Confirm Password" required=""/>
                <%= userError.getConfirmError() %></br>
                Address: <input type="text" name="address" placeholder="Address" required=""/></br>
                Birthday: <input type="text" name="birthday" placeholder="Birthday"/></br>
                Phone Number: <input type="text" name="phone" placeholder="Phone number" required=""/>
                <%= userError.getPhoneError() %></br>
                Email: <input type="text" name="email" placeholder="Email"/>
                <%= userError.getEmailError() %></br>
                <input type="submit" name="action" value="Create"/>
                <input type="reset" value="Reset"/>
                <a href="MainController?action=GetSite">Cancel</a>
            </form>
        </div>
    </body>
</html>
