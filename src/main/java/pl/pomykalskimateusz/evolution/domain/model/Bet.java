package pl.pomykalskimateusz.evolution.domain.model;

public record Bet(double value) {
    public static final double MIN_VALUE = 1;
    public static final double MAX_VALUE = 10;

    public boolean isNotValid() {
        return value < MIN_VALUE || value > MAX_VALUE;
    }
}
