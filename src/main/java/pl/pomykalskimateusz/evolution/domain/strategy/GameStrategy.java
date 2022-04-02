package pl.pomykalskimateusz.evolution.domain.strategy;

import pl.pomykalskimateusz.evolution.domain.model.Bet;
import pl.pomykalskimateusz.evolution.domain.model.GameType;

public interface GameStrategy {
    boolean isAppropriateFor(GameType mode);

    double processGame(Long userId, Bet bet);
}
