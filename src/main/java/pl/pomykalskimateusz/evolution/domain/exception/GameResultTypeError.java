package pl.pomykalskimateusz.evolution.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GameResultTypeError extends ResponseStatusException {
    public GameResultTypeError(int value) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Not found appropriate rule range for value: " + value);
    }
}
