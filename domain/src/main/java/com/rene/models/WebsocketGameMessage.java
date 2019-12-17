package com.rene.models;

import com.rene.enums.GameMessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebsocketGameMessage {
    private GameMessageType type;
    private Object message;
}