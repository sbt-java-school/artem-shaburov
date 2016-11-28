<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Recipes</title>
</head>
<body>
<c:forEach var="recipe" items="${recipes}">
    ${recipe.name} <a href="<c:url value="/cook-book/recipes/${recipe.id}"/>">Link</a><br/>
</c:forEach>
</body>
</html>
