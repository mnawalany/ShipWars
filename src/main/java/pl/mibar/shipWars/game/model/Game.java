package pl.mibar.shipWars.game.model;


import pl.mibar.shipWars.loginPage.User;

import javax.persistence.*;

@Entity
@Table(name = "game")
@SequenceGenerator(name="game_id_seq",sequenceName="game_id_seq")
public class Game {

    public enum GameStatus {
        WAITING, STARTED
    }

    @Column(name = "game_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="game_id_seq")
    private int id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_1_id")
    private Player player1;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player_2_id")
    private Player player2;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private GameStatus status = GameStatus.WAITING;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    @Transient
    public Player getPlayer(User user) {
        if (isUser1(user)) {
            return player1;
        } else if (isUser2(user)) {
            return player2;
        } else {
            return null;
        }
    }

    private boolean isUser2(User user) {
        return player2 != null && player2.getUser().getLogin().equals(user.getLogin());
    }

    private boolean isUser1(User user) {
        return player1 != null && player1.getUser().getLogin().equals(user.getLogin());
    }
}
