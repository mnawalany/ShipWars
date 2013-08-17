package pl.mibar.shipWars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mibar.shipWars.loginPage.UserSession;

import java.util.ArrayList;

@Controller
public class GamesController {

    @Autowired
    private GamesService gamesService;

    @RequestMapping(value = "main.html", method = RequestMethod.GET)
    public ModelAndView getMainPage(UserSession userSession) {
        if (userSession == null) {
            return new ModelAndView("redirect:/login.html");
        }

        ModelAndView mav = new ModelAndView("main");
        mav.addObject("waitingGames", gamesService.getWaitingGames());
        mav.addObject("userGames", gamesService.getUserGames(userSession.getUser()));
        return mav;
    }

    @RequestMapping(value = "newGame.html", method = RequestMethod.GET)
    public String createNewGame(UserSession userSession) {
        if (userSession == null) {
            return "redirect:/login.html";
        }

        Game game = gamesService.createGame(userSession.getUser());
        return "redirect:/game.html?id="+game.getId();
    }

    @RequestMapping(value = "game.html", method = RequestMethod.GET)
    public ModelAndView getGamePage(UserSession userSession, @RequestParam int id) {
        if (userSession == null) {
            return new ModelAndView("redirect:/login.html");
        }

        Game game = gamesService.getGame(id);
        if (game == null) {
            return new ModelAndView("error").addObject("message", "Game not found");
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("game", game);
        mav.addObject("player", game.getPlayer(userSession.getUser()));
        mav.addObject("userGames", gamesService.getUserGames(userSession.getUser()));

        if (game.getStatus() == Game.GameStatus.WAITING) {

            mav.setViewName("game");
            mav.addObject("isInTheGame", game.containsUser(userSession.getUser()));

        } else if (game.getStatus() == Game.GameStatus.STARTED) {
            mav.setViewName("shipSetup");
        }
        return mav;
    }


    @RequestMapping(value = "join.html", method = RequestMethod.GET)
    public ModelAndView joinGame(UserSession userSession, @RequestParam int id) {
        if (userSession == null) {
            return new ModelAndView("redirect:/login.html");
        }

        Game game = gamesService.getGame(id);
        if (game == null) {
            return new ModelAndView("error").addObject("message", "Game not found");
        }

        game.join(userSession.getUser());

        return new ModelAndView("redirect:/game.html?id="+game.getId());
    }

    @RequestMapping(value = "leave.html", method = RequestMethod.GET)
    public ModelAndView leaveGame(UserSession userSession, @RequestParam int id) {
        if (userSession == null) {
            return new ModelAndView("redirect:/login.html");
        }

        Game game = gamesService.getGame(id);
        if (game == null) {
            return new ModelAndView("error").addObject("message", "Game not found");
        }

        if (game.getPlayer(userSession.getUser()) == null) {
            return new ModelAndView("error").addObject("message", "Cannot leave this game");
        }

        gamesService.leaveGame(game, userSession.getUser());

        return new ModelAndView("redirect:/main.html");
    }

    @RequestMapping(value = "start.html", method = RequestMethod.GET)
    public ModelAndView startGame(UserSession userSession, @RequestParam int id) {
        if (userSession == null) {
            return new ModelAndView("redirect:/login.html");
        }

        Game game = gamesService.getGame(id);
        if (game == null) {
            return new ModelAndView("error").addObject("message", "Game not found");
        }

        if (game.getPlayer(userSession.getUser()) == null) {
            return new ModelAndView("error").addObject("message", "Cannot start this game");
        }

        gamesService.startGame(game, userSession.getUser());

        return new ModelAndView("redirect:/game.html?id="+game.getId());
    }

}
