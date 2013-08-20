package pl.mibar.shipWars.game.model.dto;

import pl.mibar.shipWars.game.model.Player;

public class PlayerDTO {

    private final String login;

    private final Player.PlayerStatus status;

    public PlayerDTO(Player player) {
        login = player.getUser().getLogin();
        status = player.getStatus();
    }

    public String getLogin() {
        return login;
    }

    public Player.PlayerStatus getStatus() {
        return status;
    }
}
