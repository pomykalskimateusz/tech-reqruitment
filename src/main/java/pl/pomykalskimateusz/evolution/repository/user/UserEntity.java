package pl.pomykalskimateusz.evolution.repository.user;

import lombok.*;
import pl.pomykalskimateusz.evolution.domain.model.UserBalance;
import pl.pomykalskimateusz.evolution.repository.BaseEntity;
import pl.pomykalskimateusz.evolution.repository.game.GameEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Users")
public class UserEntity extends BaseEntity {
    private BigDecimal balance;
    @OneToMany(mappedBy="user")
    private Set<GameEntity> games;

    public UserBalance getUserBalance() {
        return new UserBalance(balance);
    }
}
