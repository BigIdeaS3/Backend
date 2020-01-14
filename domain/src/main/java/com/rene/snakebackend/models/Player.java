package com.rene.snakebackend.models;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rene.snakebackend.interfaces.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Player implements DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonSerialize
    private Long id;
    private String username;
    private String password;
    @Nullable
    private String email;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
