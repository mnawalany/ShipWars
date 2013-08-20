package pl.mibar.shipWars.game.model.dto;

import pl.mibar.shipWars.game.model.Game;
import pl.mibar.shipWars.loginPage.User;

import java.util.ArrayList;
import java.util.List;

public class AllGamesDTO {

    private final List<GameDTO> allGames;

    private final List<UserGameDTO> userGames;

    public AllGamesDTO(List<Game> allGames, List<Game> userGames, User user) {
        this.allGames = new ArrayList<GameDTO>();
        for (Game game : allGames) {
            this.allGames.add(new GameDTO(game));
        }
        this.userGames = new ArrayList<UserGameDTO>();
        for (Game game : userGames) {
            this.userGames.add(new UserGameDTO(game, user));
        }
    }

    public List<GameDTO> getAllGames() {
        return allGames;
    }

    public List<UserGameDTO> getUserGames() {
        return userGames;
    }
}
