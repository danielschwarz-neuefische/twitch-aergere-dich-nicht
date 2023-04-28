package com.example.backend;

public record GameStatus(
        int playerPosition,
        Integer rolledNumber,
        Integer activePlayerNumber) {
}
