package pl.mibar.shipWars.userSession;

import org.springframework.stereotype.Component;
import pl.mibar.shipWars.loginPage.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class UserSessionWebManager {

    private static final String USER_SESSION = "userSession";

    public UserSession getSessionFromRequest(HttpServletRequest request) {
        return (UserSession) request.getSession().getAttribute(USER_SESSION);
    }

    public boolean isSessionInRequest(HttpServletRequest request) {
        return getSessionFromRequest(request) != null;
    }

    public void saveUserInSession(User user, HttpSession session) {
        session.setAttribute(USER_SESSION, new UserSession(user));
    }

}
