package pl.pomykalskimateusz.evolution.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import pl.pomykalskimateusz.evolution.domain.model.Bet;

public class InvalidBetAmountError extends ResponseStatusException {
    public InvalidBetAmountError() {
        super(HttpStatus.BAD_REQUEST, "Invalid bet amount. Should be in range between " + Bet.MIN_VALUE + " - " + Bet.MAX_VALUE);
    }
}
