package pl.mibar.shipWars;

import org.springframework.stereotype.Service;
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

    public void leaveGame(Game game, User user) {
        game.removeUser(user);
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
        game.getPlayer(user).setStatus(Player.PlayerStatus.READY);
    }
}
