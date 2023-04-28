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
        GameStatus board = gameRulesService.getGameStatus();
        Assertions.assertEquals(1, board.playerPosition());
    }

    @Test
    void expectXOnNextPosition() {
        when(diceService.rollDice()).thenReturn(3);
        gameRulesService.rollDice(playerNumber);
        GameStatus board = gameRulesService.getGameStatus();
        Assertions.assertEquals(4, board.playerPosition());
    }

    @Test
    void expectXEvenFurtherRightOnTwoDiceRolls() {
        when(diceService.rollDice()).thenReturn(1);
        gameRulesService.rollDice(playerNumber);
        when(diceService.rollDice()).thenReturn(6);
        gameRulesService.rollDice(playerNumber);
        GameStatus board = gameRulesService.getGameStatus();
        Assertions.assertEquals(8, board.playerPosition());
    }

    @Test
    void expectPos1AfterReset() {
        when(diceService.rollDice()).thenReturn(1);
        gameRulesService.rollDice(playerNumber);
        gameRulesService.reset();
        GameStatus board = gameRulesService.getGameStatus();
        Assertions.assertEquals(1, board.playerPosition());
    }
}
