package pl.mibar.shipWars.loginPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.mibar.shipWars.game.GamesService;
import pl.mibar.shipWars.userSession.UserSession;
import pl.mibar.shipWars.userSession.UserSessionWebManager;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    public static final String LOGIN_HTML = "/login.html";
    public static final String CREATE_USER_HTML = "/createUser.html";
    public static final String LOGOUT_HTML = "/logout.html";

    public static final String REDIRECT_LOGIN = "redirect:" + LOGIN_HTML;

    @Autowired
    private LoginService loginService;

    @Autowired
    private GamesService gamesService;

    @Autowired
    private UserSessionWebManager userSessionWebManager;

    @RequestMapping(value = LOGIN_HTML, method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = LOGIN_HTML, method = RequestMethod.POST)
    public ModelAndView performLogin(String login, HttpSession session) {
        ModelAndView mav = new ModelAndView("login");

        User user = loginService.getUser(login);

        if (user != null) {
            userSessionWebManager.saveUserInSession(user, session);
            mav.setViewName("redirect:/main.html");
        } else {
            mav.addObject("error", "Login not found");
        }
        return mav;
    }

    @RequestMapping(value = CREATE_USER_HTML, method = RequestMethod.POST)
    public ModelAndView createUser(String login, HttpSession session) {
        ModelAndView mav = new ModelAndView("login");

        if (!loginService.isLoginAvailable(login)) {
            mav.addObject("createUserError", "Login already exists");
        } else {
            User user = loginService.createUser(login);
            userSessionWebManager.saveUserInSession(user, session);
            mav.setViewName("redirect:/main.html");
        }
        return mav;
    }

    @RequestMapping(value = LOGOUT_HTML, method = RequestMethod.GET)
    public ModelAndView logout(User user, HttpSession session) {
        if (user != null) {
            gamesService.leaveAllGames(user);
        }
        session.invalidate();
        return new ModelAndView(REDIRECT_LOGIN);
    }

}
