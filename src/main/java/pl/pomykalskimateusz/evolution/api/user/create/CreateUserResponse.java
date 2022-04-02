package pl.pomykalskimateusz.evolution.api.user.create;

import pl.pomykalskimateusz.evolution.repository.user.UserEntity;

public record CreateUserResponse(Long id, double balance) {
    public static CreateUserResponse of(UserEntity userEntity) {
        return new CreateUserResponse(userEntity.getId(), userEntity.getBalance());
    }
}
