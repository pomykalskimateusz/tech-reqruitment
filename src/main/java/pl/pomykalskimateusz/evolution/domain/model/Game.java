package pl.pomykalskimateusz.evolution.domain.model;

import java.math.BigDecimal;
import java.util.function.Supplier;

public record Game(
        Supplier<Integer> generateRandom,
        GameRules gameRules
) {
    public GamePlayResult play(BigDecimal betAmount) {
        BigDecimal winAmount = gameRules.obtainGameResultType(generateRandom.get())
                .stream()
                .map(it -> processGameResult(it, betAmount))
                .map(GamePlayResult::winAmount)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return new GamePlayResult(winAmount);
    }

    private GamePlayResult processGameResult(GameResultType gameResult, BigDecimal betAmount) {
        return switch (gameResult) {
            case USER_WIN -> new GamePlayResult(calculateWinAmount(betAmount));
            case USER_LOSS -> new GamePlayResult(BigDecimal.ZERO);
            case FREE_BET -> play(betAmount);
        };
    }

    private BigDecimal calculateWinAmount(BigDecimal betAmount) {
        return gameRules.obtainWinResultType(generateRandom.get()).calculateWinAmount(betAmount);
    }
}
