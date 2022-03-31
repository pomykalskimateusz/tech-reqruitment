package pl.pomykalskimateusz.evolution.domain.strategy;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.domain.model.Bet;

import java.util.function.Function;

@Service
public record FreeGameStrategy(GameStrategy gameStrategy) {
    public double processGame(Long userId, Bet bet) {
        return gameStrategy.processGame(userId, bet, Function.identity());
    }
}
