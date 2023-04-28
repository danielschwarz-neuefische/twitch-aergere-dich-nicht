package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameRulesService {

    private GameStatus gameStatus = new GameStatus(1, null, 1);

    private final DiceService diceService;
    private List<Integer> usedPlayerNumbers;

    public void setUsedPlayerNumbers(List<Integer> usedPlayerNumbers) {
        this.usedPlayerNumbers = usedPlayerNumbers;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void rollDice(Object playerNumber) {
        int rolled = diceService.rollDice();
        int newPosition = gameStatus.playerPosition() + rolled;
        int playerIndex = usedPlayerNumbers.indexOf(playerNumber) + 1;
        if (playerIndex >= usedPlayerNumbers.size()) {
            playerIndex = 0;
        }
        gameStatus = new GameStatus(newPosition, rolled, usedPlayerNumbers.get(playerIndex));
    }

    public void reset() {
        gameStatus = new GameStatus(1, null, 1);
    }
}
