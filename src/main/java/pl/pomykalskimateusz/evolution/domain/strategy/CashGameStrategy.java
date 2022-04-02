package pl.pomykalskimateusz.evolution.domain.strategy;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.domain.model.Bet;
import pl.pomykalskimateusz.evolution.domain.model.GameType;

@Service
public record CashGameStrategy(GameStrategyLogic gameStrategyLogic) implements GameStrategy {
    private static final GameType type = GameType.CASH;

    @Override
    public boolean isAppropriateFor(GameType mode) {
        return type == mode;
    }

    @Override
    public double processGame(Long userId, Bet bet) {
        return gameStrategyLogic.processGame(type, userId, bet, (balance) -> balance - bet.value());
    }
}
