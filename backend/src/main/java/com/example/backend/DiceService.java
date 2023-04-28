package com.example.backend;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class DiceService {
    private Random random = new Random();

    void setSeed(int seed) {
        this.random = new Random(seed);
    }

    public int rollDice() {
        return random.nextInt(6) + 1;
    }
}
