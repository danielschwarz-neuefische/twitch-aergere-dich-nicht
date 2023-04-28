package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameRulesService {

    public static final int LENGTH_OF_BOARD = 17;
    private GameStatus gameStatus = new GameStatus(1, null);

    private final DiceService diceService;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void rollDice() {
        int rolled = diceService.rollDice();
        int newPosition = gameStatus.playerPosition() + rolled;
        gameStatus = new GameStatus(newPosition, rolled);
    }
}
