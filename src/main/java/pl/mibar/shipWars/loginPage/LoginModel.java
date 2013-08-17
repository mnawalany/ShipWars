package pl.mibar.shipWars.loginPage;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginModel {

    private Map<String, User> users = new HashMap<String, User>();

    @PostConstruct
    public void addTestUsers() {
        users.put("Michal", new User("Michal"));
        users.put("Bartek", new User("Bartek"));
    }

    public void addUser(User user) {
        users.put(user.getLogin(), user);
    }

    public User getUser(String login) {
        return users.get(login);
    }

}
