package com.example.backend;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GameRulesServiceTest {

    @Test
    void expectInitially_playerOnFirstPosition() {
        GameRulesService gameRulesService = new GameRulesService();
        String board = gameRulesService.getBoard();
        Assertions.assertEquals("X____", board);
    }

    @Test
    void expectXOnNextPosition(){
        GameRulesService gameRulesService = new GameRulesService();
        gameRulesService.rollDice();
        String board = gameRulesService.getBoard();
        Assertions.assertEquals("_X___", board);
    }

    @Test
    void expectXEvenFurtherRightOnTwoDiceRolls(){
        GameRulesService gameRulesService = new GameRulesService();
        gameRulesService.rollDice();
        gameRulesService.rollDice();
        String board = gameRulesService.getBoard();
        Assertions.assertEquals("__X__", board);
    }
}
