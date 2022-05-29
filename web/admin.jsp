<%-- 
    Document   : admin
    Created on : Mar 7, 2022, 9:07:48 PM
    Author     : To Quyen Phan
--%>

<%@page import="quyen.vegetablestore.shopping.Product"%>
<%@page import="java.util.List"%>
<%@page import="quyen.vegetablestore.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="css/style.css"/> <!-- CSS dùng chung-->
        <style>
            body{
                width: 100%;   
            }
            table{
                width: 100%;
            }
            td input{
                width: 100%;
            }
            td:last-child input{
                width: 100%;
            }
        </style>
    </head>
    <body>
        <%
            //Xác thực & phân quyền bằng gọi session
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            //Lấy role của user, nếu user khác admin thì quay về trang login
            if (loginUser == null || loginUser.getRoleID() != 1) {
                response.sendRedirect("login.jsp");
                return;
            }
            //Lấy giá trị search
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <!-- Lấy tên của user cho phần welcome -->
        <h1>Welcome: <%= loginUser.getFullName()%></h1>
        <!-- Logout bằng URL Rewriting-->
        <a href="MainController?action=Logout">Logout</a>
        <!-- Tìm kiếm tên product, gửi các parameter qua MainController bằng form -->
        <form action="MainController">
            Search: <input type="text" name="search" value="<%= search%>" required=""/>
            <input type="submit" name="action" value="Search"/>
        </form>
        <%
            //Lấy list product đã tìm được
            List<Product> listProduct = (List<Product>) request.getAttribute("LIST_PRODUCT");
            //Chỉ hiển thị khi có product
            if (listProduct != null) {
                if (listProduct.size() > 0) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Image URL</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Catagory ID</th>
                    <th>Import Date</th>
                    <th>Using Date</th>
                    <th>Status</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Product product : listProduct) {
                %>
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <input type="text" name="productID" value="<%= product.getProductID()%>" readonly=""/>
                    </td>
                    <td>
                        <input type="text" name="productName" value="<%= product.getProductName()%>" required=""/>
                    </td>
                    <td>
                        <input type="text" name="image" value="<%= product.getImage()%>" required=""/>
                    </td>
                    <td>
                        <input type="text" name="price" value="<%= product.getPrice()%>" required=""/>
                    </td>
                    <td>
                        <input type="text" name="quantity" value="<%= product.getQuantity()%>" required=""/>
                    </td>
                    <td>
                        <input type="text" name="catagoryID" value="<%= product.getCatagoryID()%>" required=""/>
                    </td>
                    <td>
                        <input type="text" name="importDate" value="<%= product.getImportDate()%>" required=""/>
                    </td>
                    <td>
                        <input type="text" name="usingDate" value="<%= product.getUsingDate()%>" required=""/>
                    </td>
                    <td>
                        <input type="text" name="status" value="<%= product.isStatus()%>" required=""/>
                    </td>
                    <td>
                        <!-- Gửi parameter Update bằng Hidden Form Fields-->
                        <input type="submit" name="action" value="Update"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>
    <%
            }
        }
        //In ra lỗi nếu có lỗi xảy ra
        String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }
    %>
    <%= error%>
</body>
</html>
