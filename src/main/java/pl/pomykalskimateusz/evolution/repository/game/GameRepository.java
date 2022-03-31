package pl.pomykalskimateusz.evolution.repository.game;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository {
    void save(GameDatabaseModel game);

    List<GameDatabaseModel> findUserGames(String userUuid);
}
