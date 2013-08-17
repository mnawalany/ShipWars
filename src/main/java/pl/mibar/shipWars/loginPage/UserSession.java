package pl.mibar.shipWars.loginPage;

public class UserSession {

    private final User user;

    public UserSession(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
