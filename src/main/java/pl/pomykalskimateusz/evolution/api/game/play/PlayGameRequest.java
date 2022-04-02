package pl.pomykalskimateusz.evolution.api.game.play;

import java.math.BigDecimal;

public record PlayGameRequest(Long userId, BigDecimal betAmount) {}
