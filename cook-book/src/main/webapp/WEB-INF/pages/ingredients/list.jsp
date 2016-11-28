<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Ingredients</title>
</head>
<body>
<c:forEach var="ingredient" items="${ingredients}">
    ${ingredient.name} <a href="<c:url value="/cook-book/ingredients/${ingredient.id}"/>">Link</a><br/>
</c:forEach>
<a href="<c:url value="/cook-book/ingredients/new"/>">Create new ingredient</a>
</body>
</html>
