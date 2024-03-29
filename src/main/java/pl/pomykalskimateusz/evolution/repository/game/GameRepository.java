package pl.pomykalskimateusz.evolution.repository.game;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<GameEntity, Long> {
    GameEntity save(GameEntity game);

    List<GameEntity> findAllByUserId(Long userId);
}
