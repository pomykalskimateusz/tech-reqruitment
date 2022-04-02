package pl.pomykalskimateusz.evolution.domain.model;

import java.math.BigDecimal;

public record Bet(BigDecimal value) {
    public static final BigDecimal MIN_VALUE = BigDecimal.ONE;
    public static final BigDecimal MAX_VALUE = BigDecimal.TEN;

    public static boolean isNotValid(BigDecimal value) {
        return value.compareTo(MIN_VALUE) < 0 || value.compareTo(MAX_VALUE) > 0;
    }
}
