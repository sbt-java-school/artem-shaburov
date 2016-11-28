<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form action="<c:url value="/cook-book/ingredients"/>" method="post">
    <input type="text" name="name" placeholder="Введите имя ингредиента"><br/>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
