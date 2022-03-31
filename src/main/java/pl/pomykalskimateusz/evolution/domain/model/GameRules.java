package pl.pomykalskimateusz.evolution.domain.model;

import java.util.List;

public record GameRules(List<GameResultType> gameRules, List<WinResultType> winRules) {
    public static GameRules defaultRules = new GameRules(
            List.of(GameResultType.USER_WIN, GameResultType.FREE_BET, GameResultType.USER_LOSS),
            List.of(WinResultType.X3, WinResultType.X10, WinResultType.X50)
    );

    public GameResultType obtainGameResultType(int value) {
        return gameRules
                .stream()
                .filter(it -> it.isInRange(value))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public WinResultType obtainWinResultType(int value) {
        return winRules
                .stream()
                .filter(it -> it.isInRange(value))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
