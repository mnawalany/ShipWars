package pl.mibar.shipWars.game.model;


import pl.mibar.shipWars.loginPage.User;

import javax.persistence.*;

@Entity
@Table(name = "player")
@SequenceGenerator(name="player_id_seq",sequenceName="player_id_seq")
public class Player {

    public enum PlayerStatus {
        WAITING, READY
    }

    @Column(name = "player_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="player_id_seq")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_login")
    private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PlayerStatus status = PlayerStatus.WAITING;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                '}';
    }
}
