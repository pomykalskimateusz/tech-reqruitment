package pl.pomykalskimateusz.evolution.api.game;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pomykalskimateusz.evolution.api.game.fetch.FetchGameResponse;
import pl.pomykalskimateusz.evolution.api.game.play.PlayGameRequest;
import pl.pomykalskimateusz.evolution.api.game.play.PlayGameResponse;
import pl.pomykalskimateusz.evolution.service.game.GameService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/games")
public record GameController(GameService gameService) {
    @PostMapping("/free")
    public @ResponseBody PlayGameResponse playFreeGame(@RequestBody() PlayGameRequest request) {
        return PlayGameResponse.of(gameService.playFreeGame(request.userId(), request.betAmount()));
    }

    @PostMapping("/cash")
    public @ResponseBody PlayGameResponse playCashGame(@RequestBody() PlayGameRequest request) {
        return PlayGameResponse.of(gameService.playCashGame(request.userId(), request.betAmount()));
    }

    @GetMapping("/{id}")
    public @ResponseBody Optional<FetchGameResponse> fetchGame(@PathVariable Long id) {
        return gameService.fetchGame(id).map(FetchGameResponse::of);
    }

    @GetMapping("/users/{id}")
    public @ResponseBody List<FetchGameResponse> fetchUserGames(@PathVariable Long id) {
        return gameService.fetchUserGames(id).stream().map(FetchGameResponse::of).collect(Collectors.toList());
    }
}
