<%-- 
    Document   : viewDetail
    Created on : Mar 8, 2022, 10:38:24 PM
    Author     : To Quyen Phan
--%>

<%@page import="quyen.vegetablestore.shopping.Product"%>
<%@page import="java.util.List"%>
<%@page import="quyen.vegetablestore.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Quyen's Vegetable Store</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style.css"/><!--CSS dùng chung-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <style>
            .news{
                background: #00ff00;
            }
        </style>
    </head>
    <body>
        <header>
            <div class="header-of-page row">
                <div class="logo col-sm-4">
                    <image src="images/Quyen's Store.png"/>
                </div>
                <div class="search col-sm-4">
                    <form action="SearchController">
                        <input type="text" placeholder="Search..." name="search"/>
                        <button type="submit"><i class="fa fa-search"></i></button>
                    </form>
                </div>
                <%
                    //Lấy thông tin người dùng (để hiển thị giống trang Site, từ phần Header sẽ giống hết
                    //Còn phần Section sẽ khác cho yêu cầu và mục đích gì đó
                    //Lấy cái thông báo add product thành công
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    Product product = (Product) request.getAttribute("PRODUCT");
                    String message = (String) request.getAttribute("MESSAGE");
                    if (message == null) {
                        message = "";
                    }
                    if (loginUser != null) { //chỉ hiển thị khi người dùng đã đăng nhập
                %>
                <div class="logging-account col-sm-4">
                    <!--Welcome user -->
                    <h3>Welcome: <%= loginUser.getFullName()%>!&nbsp;&nbsp;
                        <div class="dropdown">
                            <button><i class="far fa-user-circle"></i></button>
                            <div>
                                <!-- Logout bằng URL rewriting -->
                                <a href="MainController?action=Logout">Logout <i class="fas fa-sign-out-alt"></i></a>
                            </div>
                        </div>
                    </h3>
                </div>   
                <%
                } else { //Chỉ hiển thị khi người dùng chưa đăng nhập
                %>
                <div class="account col-sm-4">
                    <ul>
                        <li><a href="login.jsp" id="login">Login</a></li>
                        <li><a href="create.jsp" id="signup">Sign up</a></li>
                    </ul>
                </div>
                <%
                    }
                %>
            </div>
            <nav>
                <div class="navbar">
                    <a href="MainController?action=GetSite">Home</a>
                    <a href="about.jsp">About</a>
                    <a href="blog.jsp">Blog</a>
                    <a class="news" href="#">News</a>
                    <div class="category">
                        <a href="#">Category</a>
                        <div class="category-options">
                            <!-- Hiển thị tất cả các product bằng URL rewriting -->
                            <a href="MainController?action=Search Site&type=category&search=">All</a>
                            <%
                                //Lấy danh sách các category, chỉ hiển thị khi có kết quả
                                List<String> listCategory = (List<String>) session.getAttribute("LIST_CATEGORY");
                                if (listCategory != null) {
                                    if (listCategory.size() > 0) {
                                        for (String category : listCategory) {
                                            //gọi Search Site Controller với search = category vừa click
                            %>
                            <a href="MainController?action=Search Site&type=category&search=<%= category%>"><%= category%></a>
                            <%
                                        }
                                    }
                                }
                            %>
                        </div>
                    </div>
                    <a href="#">Services</a><!--Trang trí, chưa nghĩ ra ý tưởng -->
                    <a href="portfolio.jsp">Portfolio</a>
                    <a href="#">Contact</a><!--Trang trí, chưa nghĩ ra ý tưởng -->
                    <!-- Dùng URL rewriting để chuyển sang trang view cart -->
                    <a href="MainController?action=View Cart"><i class="fa fa-shopping-cart"></i></a>
                </div>
            </nav>
        </header>
        <section>
            <iframe src="https://timesofindia.indiatimes.com/life-style/health-fitness/diet/covid-meal-101-high-protein-and-balanced-diet-is-what-you-need/articleshow/88955195.cms" style="height: 100vh;width: 100%" title="Iframe Example"></iframe>
        </section>
    </body>
</html>
