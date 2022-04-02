package pl.pomykalskimateusz.evolution.domain.model;

import pl.pomykalskimateusz.evolution.domain.exception.InvalidBetAmountError;

import java.math.BigDecimal;

public record UserBet(long userId, Bet bet) {
    public static UserBet of(long userId, BigDecimal amount) {
        if(Bet.isNotValid(amount)) {
            throw new InvalidBetAmountError();
        }
        return new UserBet(userId, new Bet(amount));
    }
}
