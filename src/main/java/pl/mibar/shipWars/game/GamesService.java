package pl.mibar.shipWars.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mibar.shipWars.game.model.Game;
import pl.mibar.shipWars.game.model.GameDao;
import pl.mibar.shipWars.game.model.Player;
import pl.mibar.shipWars.game.model.PlayerDao;
import pl.mibar.shipWars.game.model.dto.AllGamesDTO;
import pl.mibar.shipWars.game.model.dto.UserGameDTO;
import pl.mibar.shipWars.loginPage.User;

import java.util.*;

@Service
public class GamesService {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private PlayerDao playerDao;

    @Transactional
    public List<Game> getWaitingGames() {
        return gameDao.getWaitingGames();
    }

    @Transactional
    public Game createGame(User user) {
        Game game = new Game();
        Player player = new Player();
        player.setUser(user);
        game.setPlayer1(player);
        gameDao.save(game);
        return game;
    }

    @Transactional
    public Game getGame(int id) {
        return gameDao.getGameById(id);
    }

    @Transactional
    public Game getExistingGame(int id) {
        Game game = getGame(id);
        if (game == null) {
            throw new GameException("Game doesn't exists");
        }
        return game;
    }

    @Transactional
    public UserGameDTO getExistingGameDao(int id, User user) {
        Game game = getGame(id);
        if (game == null) {
            throw new GameException("Game doesn't exists");
        }
        return new UserGameDTO(game, user);
    }

    @Transactional
    public void leaveGame(int id, User user) {
        Game game = getExistingGame(id);
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();

        if (player1 != null && player1.getUser().equals(user)) {
            game.setPlayer1(null);
            gameDao.save(game);
            playerDao.remove(player1);
        } else if (player2 != null && player2.getUser().equals(user)) {
            game.setPlayer2(null);
            gameDao.save(game);
            playerDao.remove(player2);
        } else {
            throw new GameException("Cannot leave this game");
        }
        if (game.getPlayer1() == null && game.getPlayer2() == null) {
            gameDao.remove(game);
        }
    }

    public void leaveAllGames(User user) {
//        for (Game game : getUserGames(user)) {
//            leaveGame(game, user);
//        }
    }

    @Transactional
    public List<Game> getUserGames(User user) {
        return gameDao.getUserGames(user);
    }

    public void startGame(Game game, User user) {
//        if (game.getPlayer(user) == null) {
//            throw new GameException("Cannot start this game");
//        }
//        game.getPlayer(user).setStatus(Player.PlayerStatus.READY);
    }

    public void joinToGame(Game game, User user) {


    }

    @Transactional
    public AllGamesDTO getAllGamesDTO(User user) {
        return new AllGamesDTO(getWaitingGames(), getUserGames(user), user);
    }
}
