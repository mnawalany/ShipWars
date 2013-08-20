<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="width: 200px; height: 100%; float: left; border-right: dotted black 1px;">

    <div>Logged as <c:out value="${login}" /> | <a href="/logout.html">logout</a></div>
    <hr />

    <h3>Your Games:</h3>
    <div id="leftUserGames">
        <div>Loading...</div>
    </div>

    <br /><br />
    <div>
        < = <a href="/main.html">All games</a>
    </div>

</div>