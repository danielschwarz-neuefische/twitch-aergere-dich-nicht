package com.example.backend;

import java.util.List;

public record PlayerStatus(
        GameStatus gameStatus,
        Integer playerNumber,
        List<Integer> playerNumbers
) {
}
