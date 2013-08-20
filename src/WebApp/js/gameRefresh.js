(function() {

    function getGamesStatus() {
        var request = $.ajax({
            url: "/status.html",
            type: "GET",
            dataType: "json",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        request.done(updateStatus);

        request.fail(function(jqXHR, textStatus) {
            alert( "Request failed: " + textStatus );
        });
    }

    function updateStatus(msg) {
        console.log(msg);
        $("#leftUserGames").children().remove();
        for (var i=0; i<msg.userGames.length; i++) {
            var game = msg.userGames[i];
            var gameElement = $("<a>").attr("href", "/game.html?id="+game.id);
            if (game.opponentPlayer) {
                gameElement.html("VS " + game.opponentPlayer.login);
            } else {
                gameElement.html("waiting for opponent");
            }
            $("#leftUserGames").append($("<div>").append(gameElement));
        }

        if ($("#allGames").length > 0) {
            updateAllGames(msg);
        }


        window.setTimeout(getGamesStatus, 3000);
    }

    function updateAllGames(msg) {
        $("#allGames").children().remove();
        for (var i=0; i<msg.allGames.length; i++) {
            var game = msg.allGames[i];
            var gameElement = $("<a>").attr("href", "/game.html?id="+game.id);
            var content = "";
            content += game.player1Login ? game.player1Login : " - ";
            content += " VS "
            content += game.player2Login ? game.player2Login : " - ";
            gameElement.html(content);
            $("#allGames").append($("<div>").append(gameElement));
        }
    }

    getGamesStatus();

})();