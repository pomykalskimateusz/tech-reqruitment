package pl.pomykalskimateusz.evolution.domain.strategy;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.domain.exception.UserNotFound;
import pl.pomykalskimateusz.evolution.domain.model.*;
import pl.pomykalskimateusz.evolution.repository.bet.BetEntity;
import pl.pomykalskimateusz.evolution.repository.bet.BetRepository;
import pl.pomykalskimateusz.evolution.repository.game.GameEntity;
import pl.pomykalskimateusz.evolution.repository.game.GameRepository;
import pl.pomykalskimateusz.evolution.repository.user.UserEntity;
import pl.pomykalskimateusz.evolution.repository.user.UserRepository;
import pl.pomykalskimateusz.evolution.service.RandomGeneratorService;

import java.math.BigDecimal;
import java.util.function.Function;

@Service
public record GameStrategyLogic(RandomGeneratorService randomGeneratorService, BetRepository betRepository,
                                GameRepository gameRepository, UserRepository userRepository) {

    public UserBalance processGame(GameType gameType, UserBet userBet, Function<UserBalance, UserBalance> calculateBalance) {
        GamePlayResult result = createGame().play(userBet.bet().value());

        UserEntity user = obtainUser(userBet.userId());
        BetEntity betEntity = buildBet(userBet.bet().value());
        UserBalance userBalance = calculateBalance.apply(user.getUserBalance()).add(result.winAmount());
        GameEntity gameEntity = GameEntity.builder()
                .type(gameType.getName())
                .winAmount(result.winAmount())
                .user(user)
                .balance(userBalance.balance())
                .bet(betEntity)
                .build();

        if(shouldUpdate(user, userBalance)) {
            user.setBalance(userBalance.balance());
            userRepository.save(user);
        }
        gameRepository.save(gameEntity);

        return new UserBalance(userBalance.balance());
    }

    private Game createGame() {
        return new Game(randomGeneratorService::generate, GameRules.defaultRules);
    }

    private boolean shouldUpdate(UserEntity user, UserBalance balance) {
        return !balance.isEquals(user.getUserBalance());
    }

    private UserEntity obtainUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
    }

    private BetEntity buildBet(BigDecimal amount) {
        return BetEntity.builder()
                .amount(amount)
                .build();
    }
}
