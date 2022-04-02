package pl.pomykalskimateusz.evolution.domain.model;

public enum GameResultType {
    USER_WIN(0, 30),
    FREE_BET(21, 30),
    USER_LOSS(31, 100);

    private final int rangeMin;
    private final int rangeMax;

    GameResultType(int rangeMin, int rangeMax) {
        this.rangeMin = rangeMin;
        this.rangeMax = rangeMax;
    }

    boolean isInRange(int value) {
        return value >= rangeMin && value <= rangeMax;
    }
}
