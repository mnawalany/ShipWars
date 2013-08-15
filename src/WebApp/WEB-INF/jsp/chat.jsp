<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>CHAT</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>

    <script type="text/javascript">

        (function() {

            var request = $.ajax({
                url: "/chatMessages.html",
                type: "GET",
                dataType: "json"
            });

            request.done(function(msg) {
                console.log(msg);
            });

            request.fail(function(jqXHR, textStatus) {
                alert( "Request failed: " + textStatus );
            });


        })();

    </script>

</head>
<body>

<h3>Chat:</h3>

<c:forEach items="${messages}" var="msg">
    <div>
        <c:out value="${msg}" />
    </div>
</c:forEach>

<form action="/chatMessage.html" method="post">
    <input type="text" name="message" />
    <input type="submit" />
</form>

</body>
</html>