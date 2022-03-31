package pl.pomykalskimateusz.evolution.domain.model;

public enum WinResultType {
    X3(0, 50, 3),
    X10(51, 75, 10),
    X50(76, 100, 50);

    private final int rangeMin;
    private final int rangeMax;
    private final int factor;

    WinResultType(int rangeMin, int rangeMax, int factor) {
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
        this.factor = factor;
    }

    boolean isInRange(int value) {
        return value >= rangeMin && value < rangeMax;
    }

    public double calculateWinAmount(double betAmount) {
        return betAmount * factor;
    }
}
