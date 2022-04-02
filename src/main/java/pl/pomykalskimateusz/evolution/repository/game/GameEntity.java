package pl.pomykalskimateusz.evolution.repository.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.pomykalskimateusz.evolution.repository.BaseEntity;
import pl.pomykalskimateusz.evolution.repository.bet.BetEntity;
import pl.pomykalskimateusz.evolution.repository.user.UserEntity;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Games")
public class GameEntity extends BaseEntity {
    private String type;
    private double winAmount;
    @OneToOne(cascade = CascadeType.ALL)
    private BetEntity bet;
    @ManyToOne
    @JoinColumn
    private UserEntity user;
}
