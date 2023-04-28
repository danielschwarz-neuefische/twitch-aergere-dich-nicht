package com.example.backend;

import java.util.List;

public record GameStatus(
        List<Integer> playerPositions,
        Integer rolledNumber,
        Integer activePlayerNumber) {
}
