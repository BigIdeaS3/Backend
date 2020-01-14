package com.rene.snakebackend.models;

import com.rene.snakebackend.interfaces.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SnakePlayer implements DTO {
    private Player player;
    private List<Location> snake;
}
