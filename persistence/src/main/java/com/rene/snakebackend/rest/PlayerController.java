package com.rene.snakebackend.rest;

import com.rene.snakebackend.handlers.PlayerHandler;
import com.rene.snakebackend.interfaces.RESTCRUD;
import com.rene.snakebackend.models.Player;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/players")
public class PlayerController implements RESTCRUD<Player,Long> {

    @Setter
    private PlayerHandler handler;

    public PlayerController() {
    }

    @Autowired
    public PlayerController(PlayerHandler handler) {
        this.handler = handler;
    }

    @Override
    public ResponseEntity create(Player entity) {
        return new ResponseEntity<>(handler.create(entity), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity read(Long id) {
        return new ResponseEntity<>(handler.read(id), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity readAll() {
        return new ResponseEntity<>(handler.readAll(), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity update(Player entity) {
        return new ResponseEntity<>(handler.update(entity), HttpStatus.valueOf(200));
    }

    @Override
    public ResponseEntity delete(Long id) {
        return new ResponseEntity<>(handler.delete(id), HttpStatus.valueOf(200));
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody Player entity) {
        return new ResponseEntity<>(handler.login(entity.getUsername(),entity.getPassword()), HttpStatus.valueOf(200));
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody Player entity) {
        return new ResponseEntity<>(handler.register(entity), HttpStatus.valueOf(200));
    }
}