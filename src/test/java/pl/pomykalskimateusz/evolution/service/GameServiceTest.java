package pl.pomykalskimateusz.evolution.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pomykalskimateusz.evolution.api.game.fetch.FetchGameResponse;
import pl.pomykalskimateusz.evolution.domain.exception.InvalidBetAmountError;
import pl.pomykalskimateusz.evolution.domain.exception.UserNotFound;
import pl.pomykalskimateusz.evolution.repository.game.GameEntity;
import pl.pomykalskimateusz.evolution.repository.user.UserEntity;
import pl.pomykalskimateusz.evolution.service.game.GameService;
import pl.pomykalskimateusz.evolution.service.user.UserService;

import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class GameServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;

    UserEntity user;

    @Before
    public void initialize() {
        user = userService.createUser();
    }

    @Test
    public void should_throw_not_found_user() {
        assertThrows(UserNotFound.class, () -> gameService.playFreeGame(-1, BigDecimal.valueOf(5)));
    }

    @Test
    public void should_throw_invalid_bet_amount_for_too_large_value_in_free_game() {
        assertThrows(InvalidBetAmountError.class, () -> gameService.playFreeGame(user.getId(), BigDecimal.valueOf(11)));
    }

    @Test
    public void should_throw_invalid_bet_amount_for_to_small_value_in_free_game() {
        assertThrows(InvalidBetAmountError.class, () -> gameService.playFreeGame(user.getId(), BigDecimal.ZERO));
    }

    @Test
    public void should_throw_invalid_bet_amount_for_too_large_value_in_cash_game() {
        assertThrows(InvalidBetAmountError.class, () -> gameService.playCashGame(user.getId(),  BigDecimal.valueOf(11)));
    }

    @Test
    public void should_throw_invalid_bet_amount_for_to_small_value_in_cash_game() {
        assertThrows(InvalidBetAmountError.class, () -> gameService.playCashGame(user.getId(), BigDecimal.ZERO));
    }

    @Test
    public void should_return_empty_games_state_for_non_existing_user() {
        List<GameEntity> games = gameService.fetchUserGames(-1);

        assertEquals(games.size(), 0);
    }

    @Test
    public void should_return_games_for_existing_user_played_games() {
        gameService.playFreeGame(user.getId(), BigDecimal.valueOf(5));
        List<GameEntity> games = gameService.fetchUserGames(user.getId());

        assertEquals(games.size(), 1);
    }

    @Test
    public void should_return_game_by_id_with_appropriate_outcome() {
        gameService.playFreeGame(user.getId(), BigDecimal.valueOf(5));
        List<GameEntity> games = gameService.fetchUserGames(user.getId());

        assertEquals(games.size(), 1);
        GameEntity game = gameService.fetchGame(games.get(0).getId());
        assertNotNull(game);
        assertEquals(game.getType(), games.get(0).getType());
        assertEquals(game.getWinAmount(), games.get(0).getWinAmount());
        assertEquals(game.getBalance(), games.get(0).getBalance());
        assertEquals(game.getBet(), games.get(0).getBet());
        assertEquals(game.getUser(), games.get(0).getUser());
    }
}
