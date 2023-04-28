package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameRulesService {

    public static final int LENGTH_OF_BOARD = 17;
    private int boardStatus = 1;

    private final DiceService diceService;

    public String getBoard() {
        StringBuilder board = new StringBuilder();

        board.append("_".repeat(Math.max(0, boardStatus - 1)));
        board.append("X");
        board.append("_".repeat(Math.max(0, LENGTH_OF_BOARD - boardStatus)));

        return board.toString();
    }

    public void rollDice() {
        boardStatus += diceService.rollDice();
    }
}
