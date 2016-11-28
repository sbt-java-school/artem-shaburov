<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h1>${recipe.name}</h1>
<p>${recipe.instruction}</p><br/>
<c:forEach items="${ingredients}" var="ingredient">
    ${ingredient.name}
</c:forEach>
<form action="<c:url value="/cook-book/recipes/${recipe.id}/edit"/>" method="post">
    <input name="name" type="text" placeholder="Введите имя рецепта" value="${recipe.name}"><br/>
    <input name="instruction" type="text" placeholder="Введите инструкцию по приготовлению"
           value="${recipe.instruction}"><br/>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
