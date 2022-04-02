package pl.pomykalskimateusz.evolution.domain.model;

import java.math.BigDecimal;

public record UserBalance(BigDecimal balance) {
    public UserBalance add(BigDecimal value) {
        return new UserBalance(balance.add(value));
    }

    public boolean isEquals(UserBalance userBalance) {
        return balance.compareTo(userBalance.balance) == 0;
    }
}
