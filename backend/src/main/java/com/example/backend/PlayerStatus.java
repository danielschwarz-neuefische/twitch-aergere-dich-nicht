package com.example.backend;

public record PlayerStatus(
        GameStatus gameStatus,
        Integer playerNumber
) {
}
