package pl.pomykalskimateusz.evolution.repository.bet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pomykalskimateusz.evolution.repository.game.GameEntity;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Bets")
public class BetEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private double amount;
    @OneToOne
    private GameEntity game;
}
