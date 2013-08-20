<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="/js/gameRefresh.js"></script>
</head>
<body>

<c:import url="leftmenu.jsp" />

<h1>Game <c:out value="${game.id}" /></h1>

<div>

    ME: <a href="/leave.html?id=${game.id}">LEAVE GAME</a>

</div>


<div>
    <div>user1:
        <c:choose>
            <c:when test="${game.player1 != null}" >
                <c:out value="${game.player1.user.login}"/> - <c:out value="${game.player1.status}"/>
            </c:when>
            <c:when test="${isInTheGame}" >
                WAITING
            </c:when>
            <c:otherwise>
                <a href="/join.html?id=${game.id}">JOIN</a>
            </c:otherwise>
        </c:choose>
    </div>
    <div>
        user2:
        <c:choose>
            <c:when test="${game.player2 != null}" >
                <c:out value="${game.player2.user.login}"/>- <c:out value="${game.player2.status}"/>
            </c:when>
            <c:when test="${isInTheGame}" >
                WAITING
            </c:when>
            <c:otherwise>
                <a href="/join.html?id=${game.id}">JOIN</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<c:if test="${isInTheGame}">
    <div>
        <a href="/leave.html?id=${game.id}">LEAVE GAME</a>
    </div>
</c:if>
<c:if test="${game.full && player.status == 'WAITING'}">
    <div>
        <a href="/start.html?id=${game.id}">START GAME</a>
    </div>
</c:if>

</body>
</html>