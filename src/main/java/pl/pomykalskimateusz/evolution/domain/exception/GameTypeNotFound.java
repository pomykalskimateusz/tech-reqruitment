package pl.pomykalskimateusz.evolution.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.pomykalskimateusz.evolution.domain.model.GameType;

public class GameTypeNotFound extends ResponseStatusException {
    public GameTypeNotFound(GameType gameType) {
        super(HttpStatus.NOT_FOUND, "Not found implementation for game type: " + gameType.getName());
    }
}
