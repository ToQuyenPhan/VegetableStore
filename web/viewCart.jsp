<%-- 
    Document   : viewCart
    Created on : Mar 9, 2022, 4:58:24 AM
    Author     : To Quyen Phan
--%>

<%@page import="java.util.List"%>
<%@page import="quyen.vegetablestore.shopping.Product"%>
<%@page import="quyen.vegetablestore.shopping.Cart"%>
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
            #viewCart{
                background: white;
            }
            #viewCart table{
                width: 60%;
            }
            #viewCart table tr th{
                text-align: center;
            }
            #viewCart table tr td{
                text-align: center;
            }
            #viewCart table tr td input{
                width: 100%;
                text-align: center;
            }
            h2{
                text-align: center;
            }
            #viewCart a button{
                background: orange;
                padding: 1rem;
                margin-top: 2rem;
                margin-left: 3rem;
                border-radius: 1rem;
            }
            #viewCart a button:hover{
                background: #ffcc00;
            }
            #viewCart a{
                color: black;
                text-decoration: none;
            }
            #yesNo1, #yesNo2{
                visibility: hidden;
                position: absolute;
                width: 50%;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background: white;
                border: 1px solid black;
                padding: 4rem;
                text-align: center;
            }
            #yesNo1 h1, #yesNo2 h1{
                margin-bottom: 5rem;
            }
            #yesNo1 a, #yesNo2 a{
                margin: 5rem;
                font-size: large;
                background: orange;
                padding: 1rem 2rem;
                border: 1px solid black;
                border-radius: 2%;
            }
            .viewCart{
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
                    //Lấy thông tin người dùng (để hiển thị giống trang home ) và lỗi khi checkout
                    //Lấy cả thông báo thành công
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    String checkoutError = (String) request.getAttribute("CHECKOUT_ERROR");
                    String success = (String) request.getAttribute("SUCCESS");
                    if (success == null) {
                        success = "";
                    }
                    if (checkoutError == null) {
                        checkoutError = "";
                    }
                    String error = (String) request.getAttribute("ERROR");
                    if (error == null) {
                        error = "";
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
                    <a class="viewCart" href="MainController?action=View Cart"><i class="fa fa-shopping-cart"></i></a>
                </div>
            </nav>
        </header>
        <section id="viewCart">
            <h1>Your selected product:</h1>
            <%
                //Lấy cart
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart != null) { //Hiển thị khi có cart
                    if (cart.getCart().size() > 0) {
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Remove</th>
                        <th>Edit</th>
                        <th>Add more!</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        double total = 0;
                        for (Product product : cart.getCart().values()) {
                            total += product.getPrice() * product.getQuantity();
                            System.out.print(product.getProductID());
                    %>
                <form action="MainController">
                    <tr>
                        <td><%= count++%></td>
                        <td>
                            <%= product.getProductID()%>
                            <input type="hidden" name="productID" value="<%= product.getProductID()%>"/>
                        </td>
                        <td><%= product.getProductName()%></td>
                        <td><%= product.getPrice()%></td>
                        <td>
                            <input type="number" name="quantity" value="<%= product.getQuantity()%>" min="1" required=""/>
                        </td>
                        <td><%= product.getPrice() * product.getQuantity()%> VNĐ</td>
                        <!--remove-->
                        <td>                           
                            <%
                                String yesNo = "yesNo" + (count - 1);
                                int removedItem = count - 1;
                            %>
                            <input onclick="yesNoOptions(<%= removedItem %>);" type="button" id="remove" value="Remove"/>
                            <div id="<%= yesNo %>">
                                <!--Bạn chắc chứ? No thì ở lại, Yes thì Remove -->
                                <h1>Are your sure to remove this product?</h1>
                                <a href="MainController?action=Remove&productID=<%= product.getProductID()%>">Yes</a>
                                <a href="viewCart.jsp">No</a>
                            </div>
                        </td>
                        <!--edit-->
                        <td>
                            <input type="submit" name="action" value="Edit"/>
                        </td>
                        <td>
                            <!-- Muốn add more product nào thì vô dòng product nhấn add more, trả về trang view detail
                                 của product đó -->
                            <a href="MainController?action=View Detail&productID=<%= product.getProductID()%>">Add more!</a>
                        </td>
                    </tr>
                </form>
                <%
                    }
                %>
                </tbody>
            </table>
            <h1>Total: <%= total%> VNĐ</h1>
            <!-- Tính tiền, gửi total để không phải sử dụng vòng lặp (Sorry, loop is my weakness)-->
            <a href="MainController?action=Checkout&total=<%= total%>"><button>Checkout</button></a></br>
            <%= checkoutError%><!-- Nếu có lỗi checkout thì thông báo, không thì "" -->
            <%
            } else { //Nếu không có product nào
            %>
            <h2>Your cart is empty!</h2>
            <%
                }
            } else {
            %>
            <%= success%><!-- Nếu thành công thì hiện thông báo, còn không thì "" -->
            <h2>Please choose your product!</h2><!-- Nhắc nhở nhẹ nhàng -->
            <%
                }
            %>
        </section>
        <script>
            function yesNoOptions(count) {
                //Chỉnh visibility thành visible để hiển thị
                switch(count){
                    case 1:
                       document.getElementById('yesNo1').style.visibility = 'visible';  
                       break;
                    case 2:
                       document.getElementById('yesNo2').style.visibility = 'visible';
                       break;
                    //Còn nữa ...
                    //Do tui ko bik xài javascript nên hơi cực xíu
                }
                
            }
        </script>
    </body>
</html>
