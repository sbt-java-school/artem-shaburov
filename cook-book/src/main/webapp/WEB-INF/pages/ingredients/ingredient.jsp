<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ingredient</title>
</head>
<body>
${ingredient.name}<br/>
<a href="<c:url value="/cook-book/ingredients/${ingredient.id}/edit"/>">Edit</a>
</body>
</html>
