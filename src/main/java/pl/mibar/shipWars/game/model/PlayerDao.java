package pl.mibar.shipWars.game.model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Player player) {
        sessionFactory.getCurrentSession().save(player);
    }

    public void remove(Player player) {
        sessionFactory.getCurrentSession().delete(player);
    }
}
