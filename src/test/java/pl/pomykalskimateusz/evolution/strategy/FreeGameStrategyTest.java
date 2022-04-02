package pl.pomykalskimateusz.evolution.strategy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.pomykalskimateusz.evolution.domain.exception.GameResultTypeError;
import pl.pomykalskimateusz.evolution.domain.exception.GameWinResultTypeError;
import pl.pomykalskimateusz.evolution.domain.model.Bet;
import pl.pomykalskimateusz.evolution.domain.model.UserBalance;
import pl.pomykalskimateusz.evolution.domain.model.UserBet;
import pl.pomykalskimateusz.evolution.domain.strategy.FreeGameStrategy;
import pl.pomykalskimateusz.evolution.domain.strategy.GameStrategyLogic;
import pl.pomykalskimateusz.evolution.repository.bet.BetRepository;
import pl.pomykalskimateusz.evolution.repository.game.GameRepository;
import pl.pomykalskimateusz.evolution.repository.user.UserEntity;
import pl.pomykalskimateusz.evolution.repository.user.UserRepository;
import pl.pomykalskimateusz.evolution.service.RandomGeneratorService;
import pl.pomykalskimateusz.evolution.service.user.UserService;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FreeGameStrategyTest {
    private static final int USER_WIN_GAME_RESULT_PERCENT = 15;
    private static final int USER_LOSS_GAME_RESULT_PERCENT = 50;
    private static final int OUT_OF_RANGE_GAME_RESULT_PERCENT = 150;
    private static final int OUT_OF_RANGE_WIN_RESULT_PERCENT = 150;

    private static final int X3_WIN_RESULT_PERCENT = 25;
    private static final int X10_WIN_RESULT_PERCENT = 70;
    private static final int X50_WIN_RESULT_PERCENT = 80;

    private static final BigDecimal BET_AMOUNT = BigDecimal.valueOf(5);
    private static final Bet BET = new Bet(BET_AMOUNT);

    @Mock
    private RandomGeneratorService randomGeneratorService;
    @Autowired
    private UserService userService;
    @Autowired
    private BetRepository betRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

    private FreeGameStrategy freeGameStrategy;
    private UserEntity user;

    @Before
    public void initialize() {
        GameStrategyLogic gameStrategyLogic = new GameStrategyLogic(
                randomGeneratorService,
                betRepository,
                gameRepository,
                userRepository
        );
        freeGameStrategy = new FreeGameStrategy(gameStrategyLogic);
        user = userService.createUser();
    }

    @Test
    public void should_throw_game_result_error_for_random_value_out_of_range(){
        when(randomGeneratorService.generate()).thenReturn(OUT_OF_RANGE_GAME_RESULT_PERCENT);
        assertThrows(GameResultTypeError.class, () -> freeGameStrategy.processGame(new UserBet(user.getId(), BET)));
    }

    @Test
    public void should_throw_win_result_error_for_random_value_out_of_range(){
        when(randomGeneratorService.generate()).thenReturn(USER_WIN_GAME_RESULT_PERCENT, OUT_OF_RANGE_WIN_RESULT_PERCENT);
        assertThrows(GameWinResultTypeError.class, () -> freeGameStrategy.processGame(new UserBet(user.getId(), BET)));
    }

    @Test
    public void should_not_deduct_balance_for_loss_game() {
        when(randomGeneratorService.generate()).thenReturn(USER_LOSS_GAME_RESULT_PERCENT);

        UserBalance userBalance = freeGameStrategy.processGame(new UserBet(user.getId(), BET));
        UserEntity updatedUser = userService.getUserById(user.getId());

        assertEquals(0, userBalance.balance().compareTo(user.getBalance()));
        assertEquals(0, updatedUser.getBalance().compareTo(user.getBalance()));
    }

    @Test
    public void should_not_deduct_balance_and_add_win_amount_for_winning_x3_game() {
        when(randomGeneratorService.generate()).thenReturn(USER_WIN_GAME_RESULT_PERCENT, X3_WIN_RESULT_PERCENT);

        UserBalance userBalance = freeGameStrategy.processGame(new UserBet(user.getId(), BET));
        UserEntity updatedUser = userService.getUserById(user.getId());

        BigDecimal modifiedUserBalance = user.getBalance().add(BET_AMOUNT.multiply(BigDecimal.valueOf(3)));

        assertEquals(0, userBalance.balance().compareTo(modifiedUserBalance));
        assertEquals(0, updatedUser.getBalance().compareTo(modifiedUserBalance));
    }

    @Test
    public void should_not_deduct_balance_and_add_win_amount_for_winning_x10_game() {
        when(randomGeneratorService.generate()).thenReturn(USER_WIN_GAME_RESULT_PERCENT, X10_WIN_RESULT_PERCENT);

        UserBalance userBalance = freeGameStrategy.processGame(new UserBet(user.getId(), BET));
        UserEntity updatedUser = userService.getUserById(user.getId());

        BigDecimal modifiedUserBalance = user.getBalance().add(BET_AMOUNT.multiply(BigDecimal.valueOf(10)));

        assertEquals(0, userBalance.balance().compareTo(modifiedUserBalance));
        assertEquals(0, updatedUser.getBalance().compareTo(modifiedUserBalance));
    }

    @Test
    public void should_not_deduct_balance_and_add_win_amount_for_winning_x50_game() {
        when(randomGeneratorService.generate()).thenReturn(USER_WIN_GAME_RESULT_PERCENT, X50_WIN_RESULT_PERCENT);

        UserBalance userBalance = freeGameStrategy.processGame(new UserBet(user.getId(), BET));
        UserEntity updatedUser = userService.getUserById(user.getId());

        BigDecimal modifiedUserBalance = user.getBalance().add(BET_AMOUNT.multiply(BigDecimal.valueOf(50)));

        assertEquals(0, userBalance.balance().compareTo(modifiedUserBalance));
        assertEquals(0, updatedUser.getBalance().compareTo(modifiedUserBalance));
    }
}
