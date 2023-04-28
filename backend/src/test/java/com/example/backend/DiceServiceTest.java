package com.example.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiceServiceTest {

    private static final int SEED = 1234;

    @Test
    void expect3onFirstRollWithDefinedSeed() {
        DiceService diceService = new DiceService();
        diceService.setSeed(SEED);
        int diceRoll = diceService.rollDice();
        Assertions.assertEquals(3, diceRoll);
    }
}
