<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>LOGIN</title>
</head>
<body>

<h3>Login:</h3>

<form action="/login.html" method="post">

    <c:if test="${error != null}">
        <div style="color: red;">
            <c:out value="${error}" />
        </div>
    </c:if>

    <div>login: <input type="text" name="login" /></div>
    <div><input type="submit" value="LOG IN"/></div>
</form>

<hr />

<form action="/createUser.html" method="post">

    <c:if test="${createUserError != null}">
        <div style="color: red;">
            <c:out value="${createUserError}" />
        </div>
    </c:if>

    <div>login: <input type="text" name="login" /></div>
    <div><input type="submit" value="CREATE"/></div>
</form>

</body>
</html>