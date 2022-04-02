package pl.pomykalskimateusz.evolution.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GameWinResultTypeError extends ResponseStatusException {
    public GameWinResultTypeError(int value) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Not found appropriate win-rule range for value: " + value);
    }
}
