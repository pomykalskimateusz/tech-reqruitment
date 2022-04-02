package pl.pomykalskimateusz.evolution.service.game;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.domain.exception.GameNotFound;
import pl.pomykalskimateusz.evolution.domain.exception.GameTypeNotFound;
import pl.pomykalskimateusz.evolution.domain.model.GameType;
import pl.pomykalskimateusz.evolution.domain.model.UserBalance;
import pl.pomykalskimateusz.evolution.domain.model.UserBet;
import pl.pomykalskimateusz.evolution.domain.strategy.GameStrategy;
import pl.pomykalskimateusz.evolution.repository.game.GameEntity;
import pl.pomykalskimateusz.evolution.repository.game.GameRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final Map<GameType, GameStrategy> gameStrategies;
    private final GameRepository gameRepository;

    public GameService(Set<GameStrategy> gameStrategies, GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.gameStrategies = gameStrategies.stream()
                .map(it -> Map.entry(it.getType(), it))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public UserBalance playFreeGame(long userId, BigDecimal betAmount) {
        return processGame(userId, betAmount, GameType.FREE);
    }

    public UserBalance playCashGame(long userId, BigDecimal betAmount) {
        return processGame(userId, betAmount, GameType.CASH);
    }

    public List<GameEntity> fetchUserGames(long userId) {
        return gameRepository.findAllByUserId(userId);
    }

    public GameEntity fetchGame(long gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new GameNotFound(gameId));
    }

    private UserBalance processGame(long userId, BigDecimal betAmount, GameType type) {
        try {
            return gameStrategies.get(type).processGame(UserBet.of(userId, betAmount));
        } catch (NullPointerException ex) {
            throw new GameTypeNotFound(type);
        }
    }
}
