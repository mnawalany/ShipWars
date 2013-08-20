package pl.mibar.shipWars.game.model.dto;

import pl.mibar.shipWars.game.model.Game;
import pl.mibar.shipWars.game.model.Player;
import pl.mibar.shipWars.loginPage.User;

public class UserGameDTO {

    private final int id;

    private final PlayerDTO userPlayer;

    private final PlayerDTO opponentPlayer;

    private final Game.GameStatus status;

    public UserGameDTO(Game game, User user) {
        id = game.getId();
        Player userPlayer = game.getPlayer(user);
        Player opponentPlayer = game.getOtherPlayer(user);
        this.userPlayer = userPlayer == null ? null : new PlayerDTO(userPlayer);
        this.opponentPlayer = opponentPlayer == null ? null : new PlayerDTO(opponentPlayer);
        status = game.getStatus();
    }

    public int getId() {
        return id;
    }

    public PlayerDTO getUserPlayer() {
        return userPlayer;
    }

    public PlayerDTO getOpponentPlayer() {
        return opponentPlayer;
    }

    public Game.GameStatus getStatus() {
        return status;
    }
}
