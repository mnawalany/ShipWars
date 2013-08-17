<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>CHAT</title>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>

    <script type="text/javascript">

        (function() {

            function getMessages() {
                var request = $.ajax({
                    url: "/chatMessages.html",
                    type: "GET",
                    dataType: "json"
                });

                request.done(updateMessages);

                request.fail(function(jqXHR, textStatus) {
                    alert( "Request failed: " + textStatus );
                });
            }

            function updateMessages(msg) {
                console.log(msg);
                $("#messages").html("");
                for (var i=0; i<msg.length; i++) {
                    $("#messages").append($("<div>").html(msg[i]));
                }
//                window.setTimeout(getMessages, 3000);
            }

            getMessages();

        })();

    </script>

</head>
<body>

<h3>Chat:</h3>

<div id="messages">
    Loading...
</div>

<form action="/chatMessage.html" method="post">
    <input type="text" name="message" />
    <input type="submit" />
</form>

</body>
</html>