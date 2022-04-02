package pl.pomykalskimateusz.evolution.domain.model;

import java.math.BigDecimal;

public record GamePlayResult(GameResultType resultType, BigDecimal winAmount) {}
