package ru.spbstu.edu.asteroids.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public void register(String username) throws IllegalPlayerUsernameException {
        Player player = new Player(username);
        repository.save(player);
    }
}
