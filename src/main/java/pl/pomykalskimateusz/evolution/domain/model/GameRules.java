package pl.pomykalskimateusz.evolution.domain.model;

import pl.pomykalskimateusz.evolution.domain.exception.GameWinResultTypeError;

import java.util.List;
import java.util.stream.Collectors;

public record GameRules(List<GameResultType> gameRules, List<WinResultType> winRules) {
    public static GameRules defaultRules = new GameRules(
            List.of(GameResultType.USER_WIN, GameResultType.FREE_BET, GameResultType.USER_LOSS),
            List.of(WinResultType.X3, WinResultType.X10, WinResultType.X50)
    );

    public List<GameResultType> obtainGameResultType(int value) {
        return gameRules
                .stream()
                .filter(it -> it.isInRange(value))
                .collect(Collectors.toList());

    }

    public WinResultType obtainWinResultType(int value) {
        return winRules
                .stream()
                .filter(it -> it.isInRange(value))
                .findFirst()
                .orElseThrow(() -> new GameWinResultTypeError(value));
    }
}
