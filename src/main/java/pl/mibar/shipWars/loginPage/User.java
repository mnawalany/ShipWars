package pl.mibar.shipWars.loginPage;

import org.hibernate.annotations.GenericGenerator;
import pl.mibar.shipWars.game.model.Player;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ship_wars_user")
public class User {

    @Column
    @Id
    private String login;

    @OneToMany
    @JoinColumn(name = "user_login")
    private List<Player> players;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (login != null ? !login.equals(user.login) : user.login != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }
}
