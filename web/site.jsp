<%-- 
    Document   : site
    Created on : Feb 8, 2022, 7:34:46 AM
    Author     : To Quyen Phan
--%>

<%@page import="java.util.Collections"%>
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
            .mySlides{
                display: inline-block;
                width: 100%;
                height: 50rem;
            }
            .header-of-detail{
                text-transform: uppercase;
                background: #33cc00;
                color: white;
                margin-top: 5rem;
                text-align: center;
                padding: 1rem;
                font-weight: bold;
            }
            .products{
                border: 1px solid black;
                box-shadow: 1rem 1rem;
                margin: 6rem;
                text-align: center;
                padding: 5rem;
                background: white;
            }
            .products img{
                display: inline-block;
                width: 100%;
                height: 50vh;
            }
            .products button{
                background: orange;
                padding: 1rem;
                margin-top: 2rem;
                border-radius: 1rem;
            }
            .products button:hover{
                background: #ffcc00;
            }
            .products a{
                color: black;
                text-decoration: none;
            }
            .home{
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
                    <form action="MainController">
                        <input type="text" placeholder="Search..." name="search"/>
                        <button type="submit" name="action" value="Search Site"><i class="fa fa-search"></i></button>
                    </form>
                </div>
                <%
                    //Lấy thông tin người dùng và danh sách tất cả các product
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    List<Product> listAllProducts = (List<Product>) request.getAttribute("LIST_ALL_PRODUCTS");
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
                    <a class="home" href="MainController?action=GetSite">Home</a>
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
                    <a href="#">Services</a>
                    <a href="portfolio.jsp">Portfolio</a>
                    <a href="#">Contact</a>
                    <!--Xem trang view cart-->
                    <a href="MainController?action=View Cart"><i class="fa fa-shopping-cart"></i></a>
                </div>
            </nav>
        </header>
        <section id="home">
            <div>
                <img class="mySlides" src="images/vegetables-on-sale-poster-template-4baf2aca55355301495909958a01dd26_screen.jpg" style="width:100%">
                <img class="mySlides" src="images/sll1.png">
                <img class="mySlides" src="images/1634211473.g_400-w-st_g.jpg">
                <img class="mySlides" src="images/1559782977.g_520-w-st_g.jpg">
            </div>
            <div class="row">
                <h1 class="col-sm-12 header-of-detail">Products</h1>
            </div>
            <div class="row all-products">
                <%
                    if (listAllProducts != null) { //Chỉ hiển thị khi có danh sách các sản phẩm
                        if (listAllProducts.size() > 0) {
                            //Sắp xếp theo giá tiền, rẻ thì xếp trước
                            Collections.sort(listAllProducts, Product.comparePrice);
                            int count = 0;
                            for (Product product : listAllProducts) {
                                if (count == 4) { //Chỉ hiển thị 4 sản phẩm rẻ nhất -> Cho đẹp
                                    break;
                                }
                                //Chỉ hiển thị sản phẩm còn số lượng và admin muốn hiển thị
                                if (product.getQuantity() > 0 && product.isStatus() != false) {
                                    count++;
                %>
                <div class="col-sm-5 products">
                    <img src="<%= product.getImage()%>">
                    <h2><%= product.getProductName()%></h2>
                    <h3><%= product.getPrice()%> VNĐ</h3>
                    <!-- Xem chi tiết sản phẩm -->
                    <a href="MainController?action=View Detail&productID=<%= product.getProductID()%>"><button>View Detail</button></a>
                </div>
                <%
                                }
                            }
                        }
                    }
                %>
            </div>
        </section>
        <footer>
            <div>
                <h1>Contact</h1>
                <h2>Email: it.toquyenphan@gmail.com</h2>
                <h2>Facebook: <a href="https://www.facebook.com/profile.php?id=100006321400254">Quyen Phan (Emma)</a></h2>
            </div>
        </footer>
        <script>
            var myIndex = 0;
            carousel();

            function carousel() {
                var i;
                var x = document.getElementsByClassName("mySlides");
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                myIndex++;
                if (myIndex > x.length) {
                    myIndex = 1
                }
                x[myIndex - 1].style.display = "block";
                setTimeout(carousel, 2000); 
            }
        </script>
    </body>
</html>
