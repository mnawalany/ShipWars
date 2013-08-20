package pl.mibar.shipWars.game.model.dto;

import pl.mibar.shipWars.game.model.Game;

public class GameDTO {

    private final int id;

    private final String player1Login;

    private final String player2Login;

    public GameDTO(Game game) {
        id = game.getId();
        player1Login = game.getPlayer1() == null ? null : game.getPlayer1().getUser().getLogin();
        player2Login = game.getPlayer2() == null ? null : game.getPlayer2().getUser().getLogin();
    }

    public int getId() {
        return id;
    }

    public String getPlayer1Login() {
        return player1Login;
    }

    public String getPlayer2Login() {
        return player2Login;
    }
}
