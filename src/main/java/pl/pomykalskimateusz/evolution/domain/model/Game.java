package pl.pomykalskimateusz.evolution.domain.model;

import java.util.function.Supplier;

public record Game(
        Supplier<Integer> generateRandom,
        GameRules gameRules
) {
    public GamePlayResult play(double betAmount) {
        GameResultType gameResult = gameRules.obtainGameResultType(generateRandom.get());

        return switch (gameResult) {
            case USER_WIN -> new GamePlayResult(gameResult, gameRules.obtainWinResultType(generateRandom.get()).calculateWinAmount(betAmount));
            case USER_LOSS -> new GamePlayResult(gameResult, 0);
            case FREE_BET -> play(betAmount);
        };
    }
}