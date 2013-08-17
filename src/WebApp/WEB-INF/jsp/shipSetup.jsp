<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<c:import url="leftmenu.jsp" />

<h1>Game <c:out value="${game.id}" /></h1>


<div>
    <div>user1: <c:out value="${game.player1.user.login}"/></div>
    <div>user2: <c:out value="${game.player2.user.login}"/></div>
</div>

<div>
    <a href="/leave.html?id=${game.id}">LEAVE GAME</a>
</div>

GAME STARTED


</html>