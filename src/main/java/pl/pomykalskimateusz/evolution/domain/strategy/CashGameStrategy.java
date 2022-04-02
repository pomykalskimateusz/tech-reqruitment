package pl.pomykalskimateusz.evolution.domain.strategy;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.domain.model.Bet;
import pl.pomykalskimateusz.evolution.domain.model.GameType;
import pl.pomykalskimateusz.evolution.domain.model.UserBalance;
import pl.pomykalskimateusz.evolution.domain.model.UserBet;

@Service
public record CashGameStrategy(GameStrategyLogic gameStrategyLogic) implements GameStrategy {
    @Override
    public GameType getType() {
        return GameType.CASH;
    }

    @Override
    public UserBalance processGame(UserBet userBet) {
        return gameStrategyLogic.processGame(getType(), userBet, (balance) -> calculate(balance, userBet.bet()));
    }

    private UserBalance calculate(UserBalance userBalance, Bet bet) {
        return new UserBalance(userBalance.balance().subtract(bet.value()));
    }
}
