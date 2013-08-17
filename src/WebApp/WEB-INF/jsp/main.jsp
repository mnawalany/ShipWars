<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>


<c:import url="leftmenu.jsp" />

<div>

    <c:forEach var="game" items="${waitingGames}">
        <div><a href="/game.html?id=${game.id}">game ${game.id}</a></div>
    </c:forEach>

</div>
<br/>
<br/>
<br/>

<a href="/newGame.html">new game</a>

</html>