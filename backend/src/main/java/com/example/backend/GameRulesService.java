package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameRulesService {

    public static final GameStatus INITIAL_STATUS = new GameStatus(List.of(1, 1, 1, 1), null, 1);
    private GameStatus gameStatus = INITIAL_STATUS;

    private final DiceService diceService;
    private List<Integer> usedPlayerNumbers;

    public void setUsedPlayerNumbers(List<Integer> usedPlayerNumbers) {
        this.usedPlayerNumbers = usedPlayerNumbers;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void rollDice(Integer playerNumber) {
        int rolled = diceService.rollDice();
        var newPositions = new ArrayList<>(gameStatus.playerPositions());
        newPositions.set(playerNumber-1, newPositions.get(playerNumber-1) + rolled);
        int playerIndex = usedPlayerNumbers.indexOf(playerNumber) + 1;
        playerIndex %= usedPlayerNumbers.size();
        gameStatus = new GameStatus(newPositions, rolled, usedPlayerNumbers.get(playerIndex));
    }

    public void reset() {
        gameStatus = INITIAL_STATUS;
    }
}
