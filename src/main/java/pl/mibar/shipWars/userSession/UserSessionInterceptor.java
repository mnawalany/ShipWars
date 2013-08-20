package pl.mibar.shipWars.userSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pl.mibar.shipWars.loginPage.LoginController;
import pl.mibar.shipWars.loginPage.User;
import pl.mibar.shipWars.userSession.UserSessionWebManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static pl.mibar.shipWars.loginPage.LoginController.CREATE_USER_HTML;
import static pl.mibar.shipWars.loginPage.LoginController.LOGIN_HTML;
import static pl.mibar.shipWars.loginPage.LoginController.LOGOUT_HTML;

public class UserSessionInterceptor extends HandlerInterceptorAdapter {

    private static final Set<String> ALLOWED_REQUESTS = new HashSet<String>(Arrays.asList(
            LOGIN_HTML, CREATE_USER_HTML, LOGOUT_HTML
    ));

    @Autowired
    private UserSessionWebManager userSessionWebManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (ALLOWED_REQUESTS.contains(request.getRequestURI())) {
            return true;
        }
        if (userSessionWebManager.isSessionInRequest(request)) {
            return true;
        }
        response.sendRedirect(LOGIN_HTML);
        return false;
//        return true;
    }
}
