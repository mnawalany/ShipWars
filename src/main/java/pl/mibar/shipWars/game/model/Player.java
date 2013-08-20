package pl.mibar.shipWars.game.model;


import pl.mibar.shipWars.loginPage.User;

public class Player {

    public enum PlayerStatus {
        WAITING, READY
    }

    private final User user;
    private PlayerListener listener;

    private PlayerStatus status = PlayerStatus.WAITING;

    public Player(User user, PlayerListener listener) {
        this.user = user;
        this.listener = listener;
    }

    public User getUser() {
        return user;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
        listener.playerStatusChanged(this, status);
    }
}
