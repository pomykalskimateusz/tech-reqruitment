package pl.pomykalskimateusz.evolution.service;

import org.springframework.stereotype.Service;

@Service
public class RandomGeneratorService {
    private static final int MIN_PERCENT = 0;
    private static final int MAX_PERCENT = 100;

    public int generate() {
        int x = generateRandomNumber(MIN_PERCENT, MAX_PERCENT);
        return x;
    }

    private int generateRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
