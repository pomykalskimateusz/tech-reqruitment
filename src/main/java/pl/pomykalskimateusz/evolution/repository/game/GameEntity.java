package pl.pomykalskimateusz.evolution.repository.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pomykalskimateusz.evolution.repository.bet.BetEntity;
import pl.pomykalskimateusz.evolution.repository.user.UserEntity;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Games")
public class GameEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private double winAmount;
    @OneToOne(cascade = CascadeType.ALL)
    private BetEntity bet;
    @ManyToOne
    @JoinColumn
    private UserEntity user;
}
