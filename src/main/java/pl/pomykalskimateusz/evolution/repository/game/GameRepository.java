package pl.pomykalskimateusz.evolution.repository.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<GameEntity, Long> {
    GameEntity save(GameEntity game);
}
