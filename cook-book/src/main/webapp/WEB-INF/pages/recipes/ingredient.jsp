<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ingredient</title>
</head>
<body>
<h1>Add ingredient</h1>
<form action="<c:url value="/cook-book/recipes/ingredient"/>" method="post">
    <input name="recipeId" value="${recipe.id}">
    <select>
        
    </select>
    <input name="quantity" type="number" step="0.1" placeholder="Выберите количество"><br/>
    <input name="units" type="text" placeholder="Введите единицу измерения"><br/>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
