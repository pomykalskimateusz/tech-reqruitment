package pl.pomykalskimateusz.evolution.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GameNotFound extends ResponseStatusException {
    public GameNotFound(Long gameId) {
        super(HttpStatus.NOT_FOUND, "Not found game: " + gameId);
    }
}
