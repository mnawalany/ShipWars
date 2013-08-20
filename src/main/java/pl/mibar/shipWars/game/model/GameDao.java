package pl.mibar.shipWars.game.model;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.mibar.shipWars.loginPage.LoginModel;
import pl.mibar.shipWars.loginPage.User;

import java.util.List;

@Repository
public class GameDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private LoginModel loginModel;

    public List<Game> getWaitingGames() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Game.class).add(Restrictions.eq("status", Game.GameStatus.WAITING))
                .list();
    }

    public Game getGameById(int id) {
        return (Game) sessionFactory.getCurrentSession().get(Game.class, id);
    }

    public void save(Game game) {
        sessionFactory.getCurrentSession().save(game);
    }

    public void remove(Game game) {
        sessionFactory.getCurrentSession().delete(game);
    }

    public List<Game> getUserGames(User user) {

        User user1 = loginModel.getUser(user.getLogin());
        System.out.println(user1.getPlayers());

        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Game where player1.user.login = :login");
        query.setParameter("login", user.getLogin());
        return query.list();
    }
}
