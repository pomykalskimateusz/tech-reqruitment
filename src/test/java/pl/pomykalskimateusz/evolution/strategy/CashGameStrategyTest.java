package pl.pomykalskimateusz.evolution.strategy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.pomykalskimateusz.evolution.domain.exception.GameWinResultTypeError;
import pl.pomykalskimateusz.evolution.domain.model.Bet;
import pl.pomykalskimateusz.evolution.domain.model.UserBalance;
import pl.pomykalskimateusz.evolution.domain.model.UserBet;
import pl.pomykalskimateusz.evolution.domain.strategy.CashGameStrategy;
import pl.pomykalskimateusz.evolution.domain.strategy.GameStrategyLogic;
import pl.pomykalskimateusz.evolution.repository.bet.BetRepository;
import pl.pomykalskimateusz.evolution.repository.game.GameRepository;
import pl.pomykalskimateusz.evolution.repository.user.UserEntity;
import pl.pomykalskimateusz.evolution.repository.user.UserRepository;
import pl.pomykalskimateusz.evolution.service.RandomGeneratorService;
import pl.pomykalskimateusz.evolution.service.user.UserService;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CashGameStrategyTest {
    private static final int USER_WIN_GAME_RESULT_PERCENT = 15;
    private static final int USER_WIN_FREE_GAME_RESULT_PERCENT = 25;
    private static final int USER_LOSS_GAME_RESULT_PERCENT = 50;
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

    private CashGameStrategy cashGameStrategy;
    private UserEntity user;

    @Before
    public void initialize() {
        GameStrategyLogic gameStrategyLogic = new GameStrategyLogic(
                randomGeneratorService,
                betRepository,
                gameRepository,
                userRepository
        );
        cashGameStrategy = new CashGameStrategy(gameStrategyLogic);
        user = userService.createUser();
    }

    @Test
    public void should_throw_win_result_error_for_random_value_out_of_range(){
        when(randomGeneratorService.generate()).thenReturn(USER_WIN_GAME_RESULT_PERCENT, OUT_OF_RANGE_WIN_RESULT_PERCENT);
        assertThrows(GameWinResultTypeError.class, () -> cashGameStrategy.processGame(new UserBet(user.getId(), BET)));
    }

    @Test
    public void should_deduct_balance_for_loss_game() {
        when(randomGeneratorService.generate()).thenReturn(USER_LOSS_GAME_RESULT_PERCENT);

        UserBalance userBalance = cashGameStrategy.processGame(new UserBet(user.getId(), BET));
        UserEntity updatedUser = userService.getUserById(user.getId());

        assertEquals(0, userBalance.balance().compareTo(user.getBalance().subtract(BET_AMOUNT)));
        assertEquals(0 , updatedUser.getBalance().compareTo(user.getBalance().subtract(BET_AMOUNT)));
    }

    @Test
    public void should_deduct_balance_and_add_win_amount_for_winning_x3_game() {
        when(randomGeneratorService.generate()).thenReturn(USER_WIN_GAME_RESULT_PERCENT, X3_WIN_RESULT_PERCENT);

        UserBalance userBalance = cashGameStrategy.processGame(new UserBet(user.getId(), BET));
        UserEntity updatedUser = userService.getUserById(user.getId());

        BigDecimal modifiedUserBalance = user.getBalance().subtract(BET_AMOUNT).add(BET_AMOUNT.multiply(BigDecimal.valueOf(3)));

        assertEquals(0, userBalance.balance().compareTo(modifiedUserBalance));
        assertEquals(0, updatedUser.getBalance().compareTo(modifiedUserBalance));
    }

    @Test
    public void should_deduct_balance_and_add_win_amount_for_winning_x10_game() {
        when(randomGeneratorService.generate()).thenReturn(USER_WIN_GAME_RESULT_PERCENT, X10_WIN_RESULT_PERCENT);

        UserBalance userBalance = cashGameStrategy.processGame(new UserBet(user.getId(), BET));
        UserEntity updatedUser = userService.getUserById(user.getId());

        BigDecimal modifiedUserBalance = user.getBalance().subtract(BET_AMOUNT).add(BET_AMOUNT.multiply(BigDecimal.valueOf(10)));

        assertEquals(0, userBalance.balance().compareTo(modifiedUserBalance));
        assertEquals(0, updatedUser.getBalance().compareTo(modifiedUserBalance));
    }

    @Test
    public void should_deduct_balance_and_add_win_amount_for_winning_x50_game() {
        when(randomGeneratorService.generate()).thenReturn(USER_WIN_GAME_RESULT_PERCENT, X50_WIN_RESULT_PERCENT);

        UserBalance userBalance = cashGameStrategy.processGame(new UserBet(user.getId(), BET));
        UserEntity updatedUser = userService.getUserById(user.getId());

        BigDecimal modifiedUserBalance = user.getBalance().subtract(BET_AMOUNT).add(BET_AMOUNT.multiply(BigDecimal.valueOf(50)));

        assertEquals(0, userBalance.balance().compareTo(modifiedUserBalance));
        assertEquals(0, updatedUser.getBalance().compareTo(modifiedUserBalance));
    }

    @Test
    public void should_deduct_balance_and_add_win_amount_for_winning_x10_and_free_x10_game() {
        when(randomGeneratorService.generate()).thenReturn(
                USER_WIN_FREE_GAME_RESULT_PERCENT, X10_WIN_RESULT_PERCENT,
                USER_WIN_GAME_RESULT_PERCENT, X10_WIN_RESULT_PERCENT
        );

        UserBalance userBalance = cashGameStrategy.processGame(new UserBet(user.getId(), BET));
        UserEntity updatedUser = userService.getUserById(user.getId());

        BigDecimal modifiedUserBalance = user.getBalance()
                .subtract(BET_AMOUNT)
                .add(BET_AMOUNT.multiply(BigDecimal.valueOf(10)))
                .add(BET_AMOUNT.multiply(BigDecimal.valueOf(10)));

        assertEquals(0, userBalance.balance().compareTo(modifiedUserBalance));
        assertEquals(0, updatedUser.getBalance().compareTo(modifiedUserBalance));
    }
}
