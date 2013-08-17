package pl.mibar.shipWars.loginPage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.mibar.shipWars.GamesService;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private GamesService gamesService;

    @RequestMapping(value = "login.html", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "login.html", method = RequestMethod.POST)
    public ModelAndView performLogin(String login, HttpSession session) {
        ModelAndView mav = new ModelAndView("login");

        User user = loginService.getUser(login);

        if (user != null) {
            session.setAttribute(UserArgumentResolver.USER_SESSION, new UserSession(user));
            mav.setViewName("redirect:/main.html");
        } else {
            mav.addObject("error", "Login not found");
        }
        return mav;
    }

    @RequestMapping(value = "createUser.html", method = RequestMethod.POST)
    public ModelAndView createUser(String login, HttpSession session) {
        ModelAndView mav = new ModelAndView("login");

        if (!loginService.isLoginAvailable(login)) {
            mav.addObject("createUserError", "Login already exists");
        } else {
            User user = loginService.createUser(login);
            session.setAttribute(UserArgumentResolver.USER_SESSION, new UserSession(user));

            mav.setViewName("redirect:/main.html");
        }
        return mav;
    }

    @RequestMapping(value = "logout.html", method = RequestMethod.GET)
    public ModelAndView logout(UserSession userSession, HttpSession session) {
        if (userSession != null) {
            gamesService.leaveAllGames(userSession.getUser());
        }
        session.removeAttribute(UserArgumentResolver.USER_SESSION);
        return new ModelAndView("redirect:/login.html");
    }

}
