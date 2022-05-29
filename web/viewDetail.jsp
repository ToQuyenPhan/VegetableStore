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
        <!-- Trang này được tạo ra nhờ sự ngu ngục của em
             Đáng lẽ em nên làm ở trang Site lun, nhưng khi phát hiện đã quá muộn
             Mà thôi chỉnh CSS có khi còn lâu hơn-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style.css"/><!--CSS dùng chung-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <style>
            #detail{
                padding-bottom: 1rem;
            }
            .product{
                position: relative;
                border: 1px solid black;
                box-shadow: 1rem 1rem;
                margin: 6rem;
                text-align: center;
                padding: 5rem;
                background: white;
            }
            .product img{
                display: inline-block;
                width: 50%;
                height: 100%;
            }
            .product{
                background: white;
                margin: 5rem;
                box-shadow: 1rem 1rem;
                border: 1px solid black;
                height: 60vh;
            }
            .product div{
                display: inline-block;
            }
            .product div h4{
                margin-top: 2rem;
                margin-bottom: 2rem
            }
            .product input{
                background: orange;
                padding: 1rem;
                margin-top: 2rem;
                border-radius: 1rem;
            }
            .product input:hover{
                background: #ffcc00;
            }
            .product a{
                position: absolute;
                top: 0;
                right: 0;
                font-size: 3rem;
                margin-right: 1rem;
                color: black;
                text-decoration: none;
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
        <section id="detail">
            <div>
                <%
                    if (product != null) { //Nếu tìm được product thì hiển thị
                %>
                <div class="row product">
                    <img class="col-sm-5" src="<%= product.getImage()%>">
                    <div class="col-sm-5">
                        <h2><%= product.getProductName()%></h2>
                        <h3><%= product.getPrice()%> VNĐ</h3>
                        <h4>Import Date: <%= product.getImportDate()%> - Using Date: <%= product.getUsingDate()%></h4>
                        <h4>Remain: <%= product.getQuantity()%></h4>
                        <form action="MainController">
                            Select your quantity:
                            <select name="quantity">
                                <%
                                    int total = 0;
                                    //Chọn từ 1 cho đến số lượng product còn trong kho
                                    for (int i = 1; i <= product.getQuantity(); i++) {
                                %>
                                <option value="<%= i%>"><%= i%></option>
                                <%
                                    }
                                %>
                            </select></br>
                            <input type="submit" name="action" value="Add To Cart">
                            <input type="submit" name="action" value="View Cart"> 
                            <input type="hidden" name="productName" value="<%= product.getProductName()%>"> 
                            <input type="hidden" name="productID" value="<%= product.getProductID()%>"> 
                            <input type="hidden" name="image" value="<%= product.getImage()%>"> 
                            <input type="hidden" name="price" value="<%= product.getPrice()%>"> 
                            <input type="hidden" name="catagoryID" value="<%= product.getCatagoryID()%>"> 
                            <input type="hidden" name="importDate" value="<%= product.getImportDate()%>"> 
                            <input type="hidden" name="usingDate" value="<%= product.getUsingDate()%>"> 
                            <input type="hidden" name="status" value="<%= product.isStatus() %>"> 
                        </form></br>
                        <%= message%><!-- In ra đã add sản phẩm gì, add bao nhiêu -->
                    </div>
                    <%
                        String search = request.getParameter("search");
                        if (search != null) { //Nếu có search thì ở trang Search truyền qua, quay về trang search
                    %>
                    <a href="MainController?action=Search Site&search=<%= search%>">X</a>
                    <%
                    } else {//Không có search thì ở trang site truyền qua, quay về site
                    %>
                    <a href="MainController?action=GetSite">X</a>
                    <%
                        }
                    %>
                </div>
                <%                    }
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
    </body>
</html>
