<%-- 
    Document   : search
    Created on : Mar 9, 2022, 2:44:59 PM
    Author     : To Quyen Phan
--%>

<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="quyen.vegetablestore.shopping.Product"%>
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
        <link rel="stylesheet" href="css/style.css"/><!--CSS dùng chung -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <style>
            .search-for{
                margin-top: 5rem;
                margin-left: 3rem;
                padding: 1rem;
                font-weight: bold;
            }
            .search-site-product{
                border: 1px solid black;
                box-shadow: 1rem 1rem;
                margin: 6rem;
                text-align: center;
                padding: 5rem;
                background: white;
            }
            .search-site-product img{
                display: inline-block;
                width: 100%;
                height: 50vh;
            }
            .search-site-product button{
                background: orange;
                padding: 1rem;
                margin-top: 2rem;
                border-radius: 1rem;
            }
            .search-site-product button:hover{
                background: #ffcc00;
            }
            .search-site-product a{
                color: black;
                text-decoration: none;
            }
            .no{
                text-align: center;
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
                    <form action="SearchSiteController">
                        <input type="text" placeholder="Search..." name="search"/>
                        <button type="submit"><i class="fa fa-search"></i></button>
                    </form>
                </div>
                <%
                    //Lấy thông tin người dùng và danh sách product, parameter search để hiển thị search for
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    List<Product> listProduct = (List<Product>) request.getAttribute("LIST_PRODUCT");
                    String search = request.getParameter("search");
                    if (search == null) {
                        search = "";
                    }
                    if (loginUser != null) {
                        //chỉ hiển thị khi người dùng đã đăng nhập
                %>
                <div class="logging-account col-sm-4">
                    <!-- Welcome user -->
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
                } else {
                    //Chỉ hiển thị khi người dùng chưa đăng nhập
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
                    <!-- Về home thì tải lại danh sách các product -->
                    <a href="MainController?action=GetSite">Home</a>
                    <a href="about.jsp">About</a>
                    <a href="blog.jsp">Blog</a>
                    <a href="news.jsp">News</a>
                    <div class="category">
                        <a href="#">Category</a>
                        <div class="category-options">
                            <!-- Hiển thị tất cả các product bằng URL rewriting -->
                            <a href="MainController?action=Search Site&type=category&search=">All</a>
                            <%
                                //Lấy danh sách các category, chỉ hiển thị khi có kết quả
                                List<String> listCategory = (List<String>) request.getAttribute("LIST_CATEGORY");
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
        <section id="search-site">
            <div class="row">
                <h1 class="col-sm-12 search-for">Search For: <%= search%></h1><!--User muốn tìm gì?-->
            </div>
            <div class="row">
                <%
                    if (listProduct != null) {
                        if (listProduct.size() > 0) {
                            //Sắp xem theo giá tiền, giá tiền nhỏ hiệ trước
                            Collections.sort(listProduct, Product.comparePrice);
                            for (Product product : listProduct) {
                                //Hàng đã hết hoặc admin đặt status là false thì không hiển thị
                                //Chỉ hiển thị product còn quantity và status bằng true
                                if (product.getQuantity() > 0 && product.isStatus() != false) {
                %>
                <div class="col-sm-5 search-site-product">
                    <img src="<%= product.getImage()%>">
                    <h2><%= product.getProductName()%></h2>
                    <h3><%= product.getPrice()%> VNĐ</h3>
                    <!--URL rewriting truyền qua MainController, để hiển thị chi tiết sản phẩm,
                        truyền parameter search cho việc quay về trang kết quả tìm kiếm -->
                    <a href="MainController?action=View Detail&productID=<%= product.getProductID()%>&search=<%= search%>">
                        <button>View Detail</button></a>
                </div>
                <%
                            }
                        }
                    }
                } else {
                %>
                <h1 class="no">No results!</h1>
                <%
                    }
                %>
            </div>
        </section>
        <!--<footer>
            <div>
                <h1>Contact</h1>
                <h2>Email: it.toquyenphan@gmail.com</h2>
                <h2>Facebook: <a href="https://www.facebook.com/profile.php?id=100006321400254">Quyen Phan (Emma)</a></h2>
            </div>
        </footer>-->
    </body>
</html>
