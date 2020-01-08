package com.rene.snakebackend.services;

import com.rene.snakebackend.interfaces.CRUD;
import com.rene.snakebackend.models.Player;
import com.rene.snakebackend.repositories.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements CRUD<Player,Long> {

    @Autowired
    @Qualifier("PlayerRepository")
    private IPlayerRepository repository;

    public Player getPlayerByUserName(String username) {
        return repository.getPlayerByUsername(username);
    }

    public Player getPlayerByUserNameAndPassword(String username, String password) {
        return repository.getPlayerByUsernameAndPassword(username, password);
    }

    @Override
    public Player create(Player entity) {
        return repository.save(entity);
    }

    @Override
    public Player read(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Iterable<Player> readAll() {
        return repository.findAll();
    }

    @Override
    public Player update(Player entity) {
        return repository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}