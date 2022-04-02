package pl.pomykalskimateusz.evolution.api.game.play;

import pl.pomykalskimateusz.evolution.domain.model.UserBalance;

import java.math.BigDecimal;

public record PlayGameResponse(BigDecimal balance) {
    public static PlayGameResponse of(UserBalance userBalance) {
        return new PlayGameResponse(userBalance.balance());
    }
}
