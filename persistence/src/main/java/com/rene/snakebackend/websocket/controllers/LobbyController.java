package com.rene.snakebackend.websocket.controllers;

import com.rene.snakebackend.handlers.WebSocketMessageHandler;
import com.rene.snakebackend.models.WebsocketLobbyMessage;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LobbyController {

    @Setter
    private final WebSocketMessageHandler handler;

    @Autowired
    public LobbyController(WebSocketMessageHandler handler) {
        this.handler = handler;
    }

    @MessageMapping("/lobbies")
    @SendTo("/topic/lobbies")
    public WebsocketLobbyMessage handleMessage(WebsocketLobbyMessage msg) {
        return handler.handleLobbyMessage(msg);
    }

}