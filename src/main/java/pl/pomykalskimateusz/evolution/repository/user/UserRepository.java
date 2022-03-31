package pl.pomykalskimateusz.evolution.repository.user;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    public double getBalance();

    public double updateBalance(String userId, double value);
}
