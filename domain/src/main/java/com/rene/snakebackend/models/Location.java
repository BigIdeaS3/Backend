package com.rene.snakebackend.models;

import com.rene.snakebackend.enums.TileType;
import com.rene.snakebackend.interfaces.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location implements DTO {
    private Integer x;
    private Integer y;
    private TileType type;

}
