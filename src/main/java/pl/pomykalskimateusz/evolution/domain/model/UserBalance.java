package pl.pomykalskimateusz.evolution.domain.model;

public record UserBalance(double balance) {
    public UserBalance add(double value) {
        return new UserBalance(balance + value);
    }

    public boolean isEquals(UserBalance userBalance) {
        return balance == userBalance.balance;
    }
}
