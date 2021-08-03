<%-- 
    Document   : detail
    Created on : Jun 1, 2021, 11:00:07 AM
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
                    <c:choose>
                        <c:when test="${requestScope.result != null}">
                            <div class="error">${requestScope.result}</div>
                        </c:when>
                        <c:otherwise>
                            <p class="title">${requestScope.article.title}</p>
                            <div class="image">
                                <img src="${requestScope.article.image}" alt="image"/>
                            </div>                    
                            <div class="content border_Bottom">
                                ${requestScope.article.content}
                            </div>
                            <div class="information clock">
                                ${requestScope.article.getInformation()}
                            </div>
                        </c:otherwise>
                    </c:choose>

                </div>
                <%@include file="right.jsp" %>
            </div>
            <div class="footer pageWidth"></div>
        </div>
    </body>
</html>