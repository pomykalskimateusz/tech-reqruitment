package pl.pomykalskimateusz.evolution.domain.strategy;

import pl.pomykalskimateusz.evolution.domain.model.GameType;
import pl.pomykalskimateusz.evolution.domain.model.UserBalance;
import pl.pomykalskimateusz.evolution.domain.model.UserBet;

public interface GameStrategy {
    GameType getType();

    UserBalance processGame(UserBet userBet);
}
