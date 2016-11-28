<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form action="<c:url value="/cook-book/recipes"/>" method="post">
    <input name="name" type="text" placeholder="Введите имя рецепта"><br/>
    <input name="instruction" type="text" placeholder="Введите инструкцию по приготовлению"><br/>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
