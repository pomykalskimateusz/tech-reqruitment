package pl.pomykalskimateusz.evolution.api.game.fetch;

import pl.pomykalskimateusz.evolution.repository.bet.BetEntity;

import java.math.BigDecimal;

public record BetModel(Long id, BigDecimal betAmount) {
    public static BetModel of(BetEntity betEntity) {
        return new BetModel(betEntity.getId(), betEntity.getAmount());
    }
}
