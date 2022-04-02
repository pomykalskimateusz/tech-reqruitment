package pl.pomykalskimateusz.evolution.service.user;

import org.springframework.stereotype.Service;
import pl.pomykalskimateusz.evolution.domain.exception.UserNotFound;
import pl.pomykalskimateusz.evolution.repository.user.UserEntity;
import pl.pomykalskimateusz.evolution.repository.user.UserRepository;

import java.math.BigDecimal;

@Service
public record UserService(UserRepository userRepository) {
    private static final BigDecimal initialBalance = BigDecimal.valueOf(5000.00);

    public UserEntity createUser() {
        return userRepository.save(buildUserEntity());
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
    }

    private UserEntity buildUserEntity() {
        return UserEntity.builder().balance(initialBalance).build();
    }
}
