<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<form action="<c:url value="/cook-book/ingredients/${ingredient.id}/edit"/>" method="post">
    <input type="text" name="name" placeholder="Введите имя ингредиента" value="${ingredient.name}"><br/>
    <input type="submit" value="Сохранить">
</form>
<a href="<c:url value="/cook-book/ingredients/${ingredient.id}/delete"/>">Delete</a>
</body>
</html>
