package pl.pomykalskimateusz.evolution.domain.model;

public record Bet(double value) {
    private static final double MIN_VALUE = 1;
    private static final double MAX_VALUE = 10;

    boolean isValid() {
        return value >= MIN_VALUE && value <= MAX_VALUE;
    }
}
