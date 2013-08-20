package pl.mibar.shipWars.game;

import org.springframework.stereotype.Service;
import pl.mibar.shipWars.game.model.Game;
import pl.mibar.shipWars.game.model.Player;
import pl.mibar.shipWars.loginPage.User;

import java.util.*;

@Service
public class GamesService {

    private Map<Integer, Game> games = new HashMap<Integer, Game>();

    public List<Game> getWaitingGames() {
        return new LinkedList(games.values());
    }

    public Game createGame(User user) {
        Game game = new Game();
        game.setUser1(user);
        games.put(game.getId(), game);
        return game;
    }


    public Game getGame(int id) {
        return games.get(id);
    }

    public Game getExistingGame(int id) {
        Game game = getGame(id);
        if (game == null) {
            throw new GameException("Game doesn't exists");
        }
        return game;
    }

    public void leaveGame(Game game, User user) {
        game.removeUser(user);
        if (game.getPlayer(user) == null) {
            throw new GameException("Cannot leave this game");
        }
        if (game.isEmpty()) {
            games.remove(game.getId());
        }
    }

    public void leaveAllGames(User user) {
        for (Game game : getUserGames(user)) {
            leaveGame(game, user);
        }
    }

    public List<Game> getUserGames(User user) {
        List<Game> userGames = new LinkedList<Game>();
        for (Game game : games.values()) {
            if (game.getPlayer(user) != null) {
                userGames.add(game);
            }
        }
        return userGames;
    }

    public void startGame(Game game, User user) {
        if (game.getPlayer(user) == null) {
            throw new GameException("Cannot start this game");
        }
        game.getPlayer(user).setStatus(Player.PlayerStatus.READY);
    }
}
