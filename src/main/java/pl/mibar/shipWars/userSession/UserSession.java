package pl.mibar.shipWars.userSession;

import pl.mibar.shipWars.loginPage.User;

public class UserSession {

    private final User user;

    public UserSession(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
