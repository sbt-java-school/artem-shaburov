<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Cookies</title>
    <%-- https://www.mkyong.com/spring-mvc/spring-mvc-how-to-include-js-or-css-files-in-a-jsp-page --%>
    <script src="<c:url value="/resources/js/send_recipe.js"/>"></script>
</head>
<body>
<form id="recipe-form" action="${pageContext.request.contextPath}/cook-book/recipes" method="post"
      onsubmit="sendJsonFromForm.call(this, event);">
    <label for="recipe-name">Имя:</label><br/>
    <input id="recipe-name" name="name" type="text"
           placeholder="Название вашего рецепта" title="Введите название вашего рецепта"/><br/>
    <label for="recipe-instruction">Описание:</label><br/>
    <input id="recipe-instruction" name="instruction" type="text"
           placeholder="Описание вашего рецепта" title="Введите описание"/><br/>
    Ингредиенты:<br/>
    <input type="text" name="ingredient-name"><input type="number" name="ingredient-quantity"><br/>
    <button onclick="addNewIngredient();">+</button>
    <input id="save-recipe" type="submit" value="Сохранить">
</form>
</body>
</html>
