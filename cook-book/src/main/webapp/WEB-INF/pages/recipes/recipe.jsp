<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Recipe</title>
</head>
<body>
<p>${recipe.name}</p>
<p>${recipe.instruction}</p>
<a href="<c:url value="/cook-book/recipes/${recipe.id}/edit"/>">Edit</a><br/>
<p>Добавить новый ингредиент</p>
<form action="<c:url value="/cook-book/ingredients"/>" method="post">
    <input type="text" name="ingredient" placeholder="Введите название ингредиента"><br/>
    <input type="number" name="quantity" step="0.1" placeholder="Выберите количество"><br/>
    <input type="text" name="units" placeholder="Выберите единицу измерения"><br/>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
