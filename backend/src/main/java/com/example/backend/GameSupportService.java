package com.example.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GameSupportService extends TextWebSocketHandler {

    private final GameRulesService gameRulesService;
    private final Set<WebSocketSession> sessions = new HashSet<>();
    private final ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        System.out.println("Connection established");
        sessions.add(session);
        publishBoardStatus();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("Connection closed");
        sessions.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        gameRulesService.rollDice();
        publishBoardStatus();
    }

    private void publishBoardStatus() throws IOException {
        String statusAsText = objectMapper.writeValueAsString(gameRulesService.getGameStatus());
        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage(statusAsText));
        }
    }
}











