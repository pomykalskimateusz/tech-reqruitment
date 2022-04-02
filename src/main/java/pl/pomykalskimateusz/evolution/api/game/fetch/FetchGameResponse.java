package pl.pomykalskimateusz.evolution.api.game.fetch;

import pl.pomykalskimateusz.evolution.repository.game.GameEntity;

import java.math.BigDecimal;

public record FetchGameResponse(Long id, String type, BigDecimal balance, BigDecimal winAmount, BetModel bet) {
    public static FetchGameResponse of(GameEntity gameEntity) {
        return new FetchGameResponse(
                gameEntity.getId(), gameEntity.getType(), gameEntity.getBalance(),
                gameEntity.getWinAmount(), BetModel.of(gameEntity.getBet())
        );
    }
}
