package pl.pomykalskimateusz.evolution.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFound extends ResponseStatusException {
    public UserNotFound(Long userId) {
        super(HttpStatus.NOT_FOUND, "Not found user: " + userId);
    }
}
