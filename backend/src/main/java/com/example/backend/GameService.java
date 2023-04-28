package com.example.backend;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Service
public class GameService extends TextWebSocketHandler {

    private String board = "X____";

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        System.out.println("Connection established");
        publishBoardStatus(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        board = "_X___";
        publishBoardStatus(session);
    }

    private void publishBoardStatus(WebSocketSession session) throws IOException {
        session.sendMessage(new TextMessage(board));
    }
}











