package com.example.backend;

import org.springframework.stereotype.Service;

@Service
public class GameRulesService {

    private int boardStatus = 1;

    public String getBoard() {
        String board = "X_____";

        switch(boardStatus){
            case 1:
                board = "X____";
                break;
            case 2:
                board = "_X___";
                break;
            case 3:
                board = "__X__";
                break;
            case 4:
                board = "___X_";
                break;
            case 5:
                board = "___X_";
                break;
            case 6:
                board = "____X";
                break;
        }

        return board;
    }

    public void rollDice() {
        boardStatus++;
    }
}
