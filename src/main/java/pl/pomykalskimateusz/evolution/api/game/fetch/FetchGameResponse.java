package pl.pomykalskimateusz.evolution.api.game.fetch;

import pl.pomykalskimateusz.evolution.repository.game.GameEntity;

public record FetchGameResponse(Long id, String type, double winAmount, BetModel bet) {
    public static FetchGameResponse of(GameEntity gameEntity) {
        return new FetchGameResponse(gameEntity.getId(), gameEntity.getType(), gameEntity.getWinAmount(), BetModel.of(gameEntity.getBet()));
    }
}
