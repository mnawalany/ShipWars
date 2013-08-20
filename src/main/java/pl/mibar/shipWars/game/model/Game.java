package pl.mibar.shipWars.game.model;


import pl.mibar.shipWars.loginPage.User;

public class Game implements PlayerListener {

    public enum GameStatus {
        WAITING, STARTED
    }

    private static int idSequence = 1;

    private final int id = idSequence++;

    private Player player1;

    private Player player2;

    private GameStatus status = GameStatus.WAITING;

    public int getId() {
        return id;
    }

    public void setUser1(User user1) {
        this.player1 = new Player(user1, this);
    }

    public void setUser2(User user2) {
        this.player2 = new Player(user2, this);
    }

    public Player getPlayer1() {
        return player1;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer(User user) {
        if (isUser1(user)) {
            return player1;
        } else if (isUser2(user)) {
            return player2;
        } else {
            return null;
        }
    }

    public Player getOtherPlayer(User user) {
        if (isUser1(user)) {
            return player2;
        } else if (isUser2(user)) {
            return player1;
        } else {
            return null;
        }
    }

    public void join(User user) {
        if (player1 == null) {
            setUser1(user);
        } else if (player2 == null) {
            setUser2(user);
        } else {
            throw new IllegalStateException("Cannot join game");
        }
    }

    public void removeUser(User user) {
        if (isUser1(user)) {
            player1 = null;
            if (player2 != null) {
                player2.setStatus(Player.PlayerStatus.WAITING);
            }
        } else if (isUser2(user)) {
            player2 = null;
            if (player1 != null) {
                player1.setStatus(Player.PlayerStatus.WAITING);
            }
        } else {
            throw new IllegalStateException("Cannot leave game");
        }
    }

    private boolean isUser2(User user) {
        return player2 != null && player2.getUser().getLogin().equals(user.getLogin());
    }

    private boolean isUser1(User user) {
        return player1 != null && player1.getUser().getLogin().equals(user.getLogin());
    }

    public boolean isEmpty() {
        return player1 == null && player2 == null;
    }

    public boolean isFull() {
        return player1 != null && player2 != null;
    }

    @Override
    public void playerStatusChanged(Player player, Player.PlayerStatus status) {
        if (player1.getStatus() == Player.PlayerStatus.READY && player2.getStatus() == Player.PlayerStatus.READY) {
            setStatus(GameStatus.STARTED);
        }

    }

    public boolean containsUser(User user) {
        return getPlayer(user) != null;
    }
}
