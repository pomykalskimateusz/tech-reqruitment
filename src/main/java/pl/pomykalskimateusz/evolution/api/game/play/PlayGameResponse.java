package pl.pomykalskimateusz.evolution.api.game.play;

import pl.pomykalskimateusz.evolution.domain.model.UserBalance;

public record PlayGameResponse(double balance) {
    public static PlayGameResponse of(UserBalance userBalance) {
        return new PlayGameResponse(userBalance.balance());
    }
}
