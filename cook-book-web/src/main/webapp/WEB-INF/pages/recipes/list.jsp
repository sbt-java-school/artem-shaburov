<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="recipes" scope="request" type="java.util.List"/>
<html>
<head>
    <title>Cookies</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/cook-book/recipes/new">New recipe</a>
<ul>
    <c:if test="${not empty recipes}">
        <c:forEach var="recipe" items="${recipes}">
            <li>${recipe.name}</li>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>
