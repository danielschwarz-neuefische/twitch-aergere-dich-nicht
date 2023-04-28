package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameRulesService {

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

    public void reset() {
        gameStatus = new GameStatus(1, null);
    }
}
