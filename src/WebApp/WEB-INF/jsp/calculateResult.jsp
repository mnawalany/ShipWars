<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

</head>
<body>

<h3>Calculator:</h3>

<c:if test="${result != null}">
    <div>
        <c:out value="${a} + ${b} = ${result}"></c:out>
    </div>
</c:if>


<form action="/calculate.html" method="POST">
    <input type="text" name="a" />
    +
    <input type="text" name="b" />
    <input type="submit">
</form>

</body>

</html>