package pl.pomykalskimateusz.evolution.repository.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pomykalskimateusz.evolution.repository.user.UserDatabaseModel;

@Data
@AllArgsConstructor
public class GameDatabaseModel {
    private String uuid;
    private double betAmount;
    private double winAmount;
    private UserDatabaseModel user;
}
