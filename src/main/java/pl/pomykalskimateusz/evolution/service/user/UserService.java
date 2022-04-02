package pl.pomykalskimateusz.evolution.service.user;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.repository.user.UserEntity;
import pl.pomykalskimateusz.evolution.repository.user.UserRepository;

@Service
public record UserService(UserRepository userRepository) {
    private static final double initialBalance = 5000;

    public UserEntity createUser() {
        return  userRepository.save(buildUserEntity());
    }

    private UserEntity buildUserEntity() {
        return UserEntity.builder().balance(initialBalance).build();
    }
}
