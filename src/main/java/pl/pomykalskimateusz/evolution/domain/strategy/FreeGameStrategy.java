package pl.pomykalskimateusz.evolution.domain.strategy;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.domain.model.GameType;
import pl.pomykalskimateusz.evolution.domain.model.UserBalance;
import pl.pomykalskimateusz.evolution.domain.model.UserBet;

import java.util.function.Function;

@Service
public record FreeGameStrategy(GameStrategyLogic gameStrategyLogic) implements GameStrategy {
    @Override
    public GameType getType() {
        return GameType.FREE;
    }

    @Override
    public UserBalance processGame(UserBet userBet) {
        return gameStrategyLogic.processGame(getType(), userBet, Function.identity());
    }
}
