package com.rene.websocket.controllers;

import com.rene.handlers.MessageHandler;
import com.rene.models.WebsocketLobbyMessage;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LobbyController {

    @Setter
    private final MessageHandler handler;

    @Autowired
    public LobbyController(MessageHandler handler) {
        this.handler = handler;
    }

    @MessageMapping("/lobbies")
    @SendTo("/topic/lobbies")
    public WebsocketLobbyMessage handleMessage(WebsocketLobbyMessage msg) {
        return handler.handleLobbyMessage(msg);
    }

}