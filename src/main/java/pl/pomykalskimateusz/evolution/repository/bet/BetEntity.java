package pl.pomykalskimateusz.evolution.repository.bet;

import lombok.*;
import pl.pomykalskimateusz.evolution.repository.BaseEntity;
import pl.pomykalskimateusz.evolution.repository.game.GameEntity;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Bets")
public class BetEntity extends BaseEntity {
    private double amount;
    @OneToOne
    private GameEntity game;
}
