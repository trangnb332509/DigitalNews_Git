<%-- 
    Document   : error
    Created on : May 22, 2021, 11:13:02 AM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Digital News</title>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <%@include file="header.jsp" %>
            <p class="error">${requestScope.result}</p>
            <div class="footer"></div>
        </div>
    </body>
</html>
