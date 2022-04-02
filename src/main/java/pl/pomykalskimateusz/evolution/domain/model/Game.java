package pl.pomykalskimateusz.evolution.domain.model;

import java.math.BigDecimal;
import java.util.function.Supplier;

public record Game(
        Supplier<Integer> generateRandom,
        GameRules gameRules
) {
    public GamePlayResult play(BigDecimal betAmount) {
        GameResultType gameResult = gameRules.obtainGameResultType(generateRandom.get());

        return switch (gameResult) {
            case USER_WIN -> new GamePlayResult(gameResult, gameRules.obtainWinResultType(generateRandom.get()).calculateWinAmount(betAmount));
            case USER_LOSS -> new GamePlayResult(gameResult, BigDecimal.ZERO);
            case FREE_BET -> play(betAmount);
        };
    }
}
