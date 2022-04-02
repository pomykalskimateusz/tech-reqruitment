package pl.pomykalskimateusz.evolution.api.game.fetch;

import pl.pomykalskimateusz.evolution.repository.bet.BetEntity;

public record BetModel(Long id, double betAmount) {
    public static BetModel of(BetEntity betEntity) {
        return new BetModel(betEntity.getId(), betEntity.getAmount());
    }
}
