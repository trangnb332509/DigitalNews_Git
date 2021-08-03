<%-- 
    Document   : right
    Created on : May 22, 2021, 11:54:01 AM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Digital News</title>
        <link href="css/right.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="right">
            <div>
                <p class="title">Digital news</p>
            </div>
            <div class="content">
                ${requestScope.mostRecentArticle.description}
            </div>
            <div>
                <p class="title">Search</p>
            </div>
            <form action="Search" method="GET">
                <input class="input" type="text" name="txtSearch">
                <input class="submit" type="submit" value="Go">
            </form>
            <div>
                <p class="title">Last Articles</p>
            </div>
            <ul class="fiveRecentArticles">
                <c:forEach items="${requestScope.fiveRecentArticle}" var="article">
                    <li class="item">
                        <a href="detail?id=${article.id}">${article.title}</a><br>
                    </li>
                </c:forEach>
            </ul>            
        </div>
    </body>
</html>
