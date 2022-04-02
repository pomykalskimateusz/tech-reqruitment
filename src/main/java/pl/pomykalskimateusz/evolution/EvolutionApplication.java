package pl.pomykalskimateusz.evolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.pomykalskimateusz.evolution.service.RandomGeneratorService;

import java.util.function.Supplier;

@EnableJpaRepositories
@SpringBootApplication
public class EvolutionApplication {
    public static void main(String[] args) {
        SpringApplication.run(EvolutionApplication.class, args);
    }
}
