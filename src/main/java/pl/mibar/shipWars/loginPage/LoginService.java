package pl.mibar.shipWars.loginPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginModel model;

    public User getUser(String login) {
        return model.getUser(login);
    }

    public boolean isLoginAvailable(String login) {
        return model.getUser(login) == null;
    }

    public User createUser(String login) {
        User user = new User(login);
        model.addUser(user);
        return user;
    }


}
