package pl.pomykalskimateusz.evolution.api.user.create;

import pl.pomykalskimateusz.evolution.repository.user.UserEntity;

import java.math.BigDecimal;

public record CreateUserResponse(Long id, BigDecimal balance) {
    public static CreateUserResponse of(UserEntity userEntity) {
        return new CreateUserResponse(userEntity.getId(), userEntity.getBalance());
    }
}
