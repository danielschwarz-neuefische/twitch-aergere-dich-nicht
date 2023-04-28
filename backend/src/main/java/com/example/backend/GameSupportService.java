package com.example.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.sql.Array;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GameSupportService extends TextWebSocketHandler {

    private final GameRulesService gameRulesService;
    private final Set<WebSocketSession> sessions = new HashSet<>();
    private final ObjectMapper objectMapper;
    private static final List<Integer> ALL_PLAYER_NUMBERS = List.of(1, 2, 3, 4);
    private final List<Integer> availablePlayerNumbers = new LinkedList<>(ALL_PLAYER_NUMBERS);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        System.out.println("Connection established");
        sessions.add(session);
        if (!availablePlayerNumbers.isEmpty()) {
            var playerNumber = availablePlayerNumbers.remove(0);
            session.getAttributes().put("playerNumber", playerNumber);
        }
        publishBoardStatus();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws IOException {
        System.out.println("Connection closed");
        var playerNumber = (Integer) session.getAttributes().get("playerNumber");
        if (playerNumber != null) {
            availablePlayerNumbers.add(playerNumber);
            Collections.sort(availablePlayerNumbers);
        }
        sessions.remove(session);
        publishBoardStatus();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        switch (message.getPayload()) {
            case "reset":
                gameRulesService.reset();
                break;
            case "rollDice":
                gameRulesService.rollDice();
                break;
            default:
                System.out.println("Unknown message: " + message.getPayload());
        }
        publishBoardStatus();
    }

    private void publishBoardStatus() throws IOException {
        var gameStatus = gameRulesService.getGameStatus();
        for (WebSocketSession session : sessions) {
            var usedPlayerNumbers = getUsedPlayerNumbers();
            var playerStatus = new PlayerStatus(
                    gameStatus,
                    (Integer) session.getAttributes().get("playerNumber"),
                    usedPlayerNumbers
            );
            String statusAsText = objectMapper.writeValueAsString(playerStatus);
            session.sendMessage(new TextMessage(statusAsText));
        }
    }

    private ArrayList<Integer> getUsedPlayerNumbers() {
        var usedPlayerNumbers = new ArrayList<>(ALL_PLAYER_NUMBERS);
        usedPlayerNumbers.removeAll(availablePlayerNumbers);
        return usedPlayerNumbers;
    }
}











