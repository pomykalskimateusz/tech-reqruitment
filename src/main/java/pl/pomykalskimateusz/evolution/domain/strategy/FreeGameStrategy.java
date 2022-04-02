package pl.pomykalskimateusz.evolution.domain.strategy;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.domain.model.Bet;
import pl.pomykalskimateusz.evolution.domain.model.GameType;

import java.util.function.Function;

@Service
public record FreeGameStrategy(GameStrategyLogic gameStrategyLogic) implements GameStrategy {
    private static final GameType type = GameType.FREE;

    @Override
    public boolean isAppropriateFor(GameType mode) {
        return type == mode;
    }

    @Override
    public double processGame(Long userId, Bet bet) {
        return gameStrategyLogic.processGame(type, userId, bet, Function.identity());
    }
}
