package pl.pomykalskimateusz.evolution.repository.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.pomykalskimateusz.evolution.repository.game.GameEntity;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double balance;
    @OneToMany(mappedBy="user")
    private Set<GameEntity> games;
}
