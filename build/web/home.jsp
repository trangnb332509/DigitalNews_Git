<%-- 
    Document   : home
    Created on : May 16, 2021, 3:33:29 PM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="main">
                <div class="left">
                    <p class="title">${requestScope.mostRecentArticle.title}</p>
                    <div class="image">
                        <img src="${requestScope.mostRecentArticle.image}" alt="image"/>
                    </div>                    
                    <div class="content border_Bottom">
                        ${requestScope.mostRecentArticle.content}
                    </div>
                    <div class="information clock">
                        ${requestScope.mostRecentArticle.getInformation()}
                    </div>
                </div>
                <%@include file="right.jsp" %>
            </div>
            <div class="footer"></div>
        </div>
    </body>
</html>
