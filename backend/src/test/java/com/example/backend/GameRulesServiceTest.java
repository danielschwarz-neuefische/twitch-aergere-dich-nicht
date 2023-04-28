package com.example.backend;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameRulesServiceTest {

    private final DiceService diceService = mock(DiceService.class);
    private final GameRulesService gameRulesService = new GameRulesService(diceService);

    @Test
    void expectInitially_playerOnFirstPosition() {
        String board = gameRulesService.getBoard();
        Assertions.assertEquals("X________________", board);
    }

    @Test
    void expectXOnNextPosition() {
        when(diceService.rollDice()).thenReturn(3);
        gameRulesService.rollDice();
        String board = gameRulesService.getBoard();
        Assertions.assertEquals("___X_____________", board);
    }

    @Test
    void expectXEvenFurtherRightOnTwoDiceRolls() {
        when(diceService.rollDice()).thenReturn(1);
        gameRulesService.rollDice();
        when(diceService.rollDice()).thenReturn(6);
        gameRulesService.rollDice();
        String board = gameRulesService.getBoard();
        Assertions.assertEquals("_______X_________", board);
    }
}
