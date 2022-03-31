package pl.pomykalskimateusz.evolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.pomykalskimateusz.evolution.service.RandomGeneratorService;

import java.util.function.Supplier;

@SpringBootApplication
public class EvolutionApplication {
    public static void main(String[] args) {
        SpringApplication.run(EvolutionApplication.class, args);
    }
}
