package pl.pomykalskimateusz.evolution.domain.model;

import pl.pomykalskimateusz.evolution.domain.exception.InvalidBetAmountError;

public record UserBet(long userId, Bet bet) {
    public static UserBet of(long userId, double amount) {
        if(Bet.isNotValid(amount)) {
            throw new InvalidBetAmountError();
        }
        return new UserBet(userId, new Bet(amount));
    }
}
