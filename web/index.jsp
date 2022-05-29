<%-- 
    Document   : index
    Created on : Mar 8, 2022, 8:50:32 PM
    Author     : To Quyen Phan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome To My Store</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css"/>
        <style>
            @keyframes change{
                0%{
                    background-image: url("images/2-2-2-2foodgroups_vegetables_detailfeature_thumb.jpg")
                }
                50%{
                    background-image: url("images/643188-gettyimages-153946385.jpg")
                }
                100%{
                    background-image: url("images/istockphoto-589415708-612x612.jpg")
                }
            }
        </style>
    </head>
    <body>
        <div class="theme">
            <div class='content'>
                <!--Trang welcome đầu tiên -->
                <h1>Welcome to Quyen's Vegetable Store</h1></br>
                <h2>Are you ready to explore my store?</h2></br>
                <!-- Dùng URL Rewriting gửi action để lấy toàn bộ danh sách product hiển thị lên site -->
                <a href="MainController?action=GetSite">Let's get it</a>
            </div>
        </div>
    </body>
</html>
