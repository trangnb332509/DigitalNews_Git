<%-- 
    Document   : search
    Created on : May 21, 2021, 9:02:34 AM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Digital News</title>
        <script src="js/pagings.js" type="text/javascript"></script>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <link href="css/search.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <%@include file="header.jsp" %>
            <div class="main">
                <div class="left">
                    <c:choose>
                        <c:when test="${requestScope.result != null}">
                            <p class="error">${requestScope.result}</p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${requestScope.articles}" var="article">
                                <a class="title text" href="detail?id=${article.id}">${article.title}</a>
                                <div class="forms">
                                    <div class="image_search">
                                        <img src="${article.image}" alt="image"/>
                                    </div>
                                    <div class="content">
                                        ${article.description}
                                    </div>
                                </div>
                                <hr>
                            </c:forEach>
                            <div id="botpager" class="pager"></div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <%@include file="right.jsp" %>
            </div>
            <div class="footer"></div>
        </div>
    </body>
    <script>
        renderPager("botpager",${requestScope.pageindex},${requestScope.totalPage}, 2, "${requestScope.keyword}");
    </script>
</html>
