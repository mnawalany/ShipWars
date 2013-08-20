package pl.mibar.shipWars.game;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mibar.shipWars.game.model.Game;
import pl.mibar.shipWars.game.model.dto.AllGamesDTO;
import pl.mibar.shipWars.game.model.dto.UserGameDTO;
import pl.mibar.shipWars.loginPage.User;
import pl.mibar.shipWars.userSession.UserSession;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transaction;
import java.io.IOException;

@Controller
public class GamesController {

    @Autowired
    private GamesService gamesService;

    @RequestMapping(value = "main.html", method = RequestMethod.GET)
    public ModelAndView getMainPage(User user) {
        ModelAndView mav = new ModelAndView("main");
        return mav;
    }

    @RequestMapping(value = "newGame.html", method = RequestMethod.GET)
    public ModelAndView createNewGame(User user) {
        Game game = gamesService.createGame(user);
        return redirectToGamePage(game);
    }

    @RequestMapping(value = "game.html", method = RequestMethod.GET)
    public ModelAndView getGamePage(User user, @RequestParam int id) {
        UserGameDTO game = gamesService.getExistingGameDao(id, user);
        ModelAndView mav = new ModelAndView("game");
        mav.addObject("game", game);
        return mav;
    }

    @RequestMapping(value = "join.html", method = RequestMethod.GET)
    public ModelAndView joinGame(User user, @RequestParam int id) {
        Game game = gamesService.getExistingGame(id);
        gamesService.joinToGame(game, user);
        return redirectToGamePage(game);
    }

    @RequestMapping(value = "leave.html", method = RequestMethod.GET)
    public ModelAndView leaveGame(User user, @RequestParam int id) {
        Game game = gamesService.getExistingGame(id);
        gamesService.leaveGame(id, user);
        return new ModelAndView("redirect:/main.html");
    }

    @RequestMapping(value = "start.html", method = RequestMethod.GET)
    public ModelAndView startGame(User user, @RequestParam int id) {
        Game game = gamesService.getExistingGame(id);
        gamesService.startGame(game, user);
        return redirectToGamePage(game);
    }

    @RequestMapping(value = "status.html")
    @ResponseBody
    public String getAllGames(User user, HttpServletRequest request) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(gamesService.getAllGamesDTO(user));
    }

    @ExceptionHandler(GameException.class)
    public ModelAndView handleGameException(GameException ex) {
        return new ModelAndView("error").addObject("message", ex.getMessage());
    }

    private ModelAndView redirectToGamePage(Game game) {
        return new ModelAndView("redirect:/game.html?id="+game.getId());
    }

}
