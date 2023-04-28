package com.example.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class GameSupportService extends TextWebSocketHandler {

    private final GameRulesService gameRulesService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        System.out.println("Connection established");
        publishBoardStatus(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        gameRulesService.rollDice();
        publishBoardStatus(session);
    }

    private void publishBoardStatus(WebSocketSession session) throws IOException {
        session.sendMessage(new TextMessage(gameRulesService.getBoard()));
    }
}











