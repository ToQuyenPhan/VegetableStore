<%-- 
    Document   : error
    Created on : Mar 7, 2022, 12:08:17 AM
    Author     : To Quyen Phan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <%
            //In ra một số lỗi ít gặp hơn
            String error = (String) request.getAttribute("ERROR_MESSAGE");
            if (error != null) {
        %>
            <h1><%= error %></h1>
        <%
            }
        %>
    </body>
</html>
