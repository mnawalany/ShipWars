<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="width: 200px; height: 100%; float: left; border-right: dotted black 1px;">

    <div>Logged as <c:out value="${login}" /> | <a href="/logout.html">logout</a></div>
    <hr />

    <h3>Your Games:</h3>
    <c:forEach items="${userGames}" var="userGame">
        <div>
            <c:set var="gameStyle" value="" />
            <c:if test="${userGame.id == game.id}">
                <c:set var="gameStyle" value="background: #aaaaaa;" />
            </c:if>
            <div style="${gameStyle}"><a href="/game.html?id=${userGame.id}">game ${userGame.id}</a></div>
        </div>
    </c:forEach>
    <br /><br />
    <div>
        < = <a href="/main.html">All games</a>
    </div>

</div>