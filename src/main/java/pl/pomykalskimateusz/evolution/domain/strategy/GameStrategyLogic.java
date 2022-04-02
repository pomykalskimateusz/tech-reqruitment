package pl.pomykalskimateusz.evolution.domain.strategy;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.domain.exception.InvalidBetAmountError;
import pl.pomykalskimateusz.evolution.domain.exception.UserNotFound;
import pl.pomykalskimateusz.evolution.domain.model.*;
import pl.pomykalskimateusz.evolution.repository.bet.BetEntity;
import pl.pomykalskimateusz.evolution.repository.bet.BetRepository;
import pl.pomykalskimateusz.evolution.repository.game.GameEntity;
import pl.pomykalskimateusz.evolution.repository.game.GameRepository;
import pl.pomykalskimateusz.evolution.repository.user.UserEntity;
import pl.pomykalskimateusz.evolution.repository.user.UserRepository;
import pl.pomykalskimateusz.evolution.service.RandomGeneratorService;

import java.util.function.Function;

@Service
public record GameStrategyLogic(RandomGeneratorService randomGeneratorService, BetRepository betRepository,
                                GameRepository gameRepository, UserRepository userRepository) {

    public double processGame(GameType gameType, Long userId, Bet bet, Function<Double, Double> calculateBalance) {
        if(bet.isNotValid()) {
            throw new InvalidBetAmountError();
        }

        GamePlayResult result = createGame().play(bet.value());

        UserEntity user = obtainUser(userId);
        BetEntity betEntity = buildBet(bet.value());
        GameEntity gameEntity = GameEntity.builder()
                .type(gameType.getName())
                .winAmount(result.winAmount())
                .user(user)
                .bet(betEntity)
                .build();
        double balance = calculateBalance.apply(user.getBalance()) + result.winAmount();

        if(shouldUpdate(user, balance)) {
            user.setBalance(balance);
            userRepository.save(user);
        }
        gameRepository.save(gameEntity);

        return balance;
    }

    private Game createGame() {
        return new Game(randomGeneratorService::generate, GameRules.defaultRules);
    }

    private boolean shouldUpdate(UserEntity user, double balance) {
        return user.getBalance() != balance;
    }

    private UserEntity obtainUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
    }

    private BetEntity buildBet(double amount) {
        return BetEntity.builder()
                .amount(amount)
                .build();
    }
}