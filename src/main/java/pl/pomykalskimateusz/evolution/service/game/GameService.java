package pl.pomykalskimateusz.evolution.service.game;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.domain.exception.GameTypeNotFound;
import pl.pomykalskimateusz.evolution.domain.model.Bet;
import pl.pomykalskimateusz.evolution.domain.model.GameType;
import pl.pomykalskimateusz.evolution.domain.strategy.GameStrategy;
import pl.pomykalskimateusz.evolution.repository.game.GameEntity;
import pl.pomykalskimateusz.evolution.repository.game.GameRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public record GameService(Set<GameStrategy> gameStrategies,
                          GameRepository gameRepository) {
    public double playFreeGame(Long userId, double betAmount) {
        return processGame(userId, betAmount, GameType.FREE);
    }

    public double playCashGame(Long userId, double betAmount) {
        return processGame(userId, betAmount, GameType.CASH);
    }

    public List<GameEntity> fetchUserGames(Long userId) {
        return gameRepository.findAllByUserId(userId);
    }

    public Optional<GameEntity> fetchGame(Long gameId) {
        return gameRepository.findById(gameId);
    }

    private double processGame(Long userId, double betAmount, GameType type) {
        return gameStrategies.stream()
                .filter(it -> it.isAppropriateFor(type))
                .findFirst()
                .map(it -> it.processGame(userId, new Bet(betAmount)))
                .orElseThrow(() -> new GameTypeNotFound(type));
    }
}
