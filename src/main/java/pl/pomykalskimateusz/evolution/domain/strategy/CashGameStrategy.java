package pl.pomykalskimateusz.evolution.domain.strategy;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.domain.model.Bet;

@Service
public record CashGameStrategy(GameStrategy gameStrategy) {
    public double processGame(Long userId, Bet bet) {
        return gameStrategy.processGame(userId, bet, (balance) -> balance - bet.value());
    }
}
