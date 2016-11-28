<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/cook-book/recipes" method="post">
    <input id="recipe-name" name="name" type="text" placeholder="Введите имя рецепта"><br/>
    <input id="recipe-instruction" name="instruction" type="text"
           placeholder="Введите инструкцию по приготовлению"><br/>
    <input id="save-recipe" name="save-button" type="submit" value="Сохранить">
</form>
</body>
</html>
