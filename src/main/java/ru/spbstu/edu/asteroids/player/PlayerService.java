package ru.spbstu.edu.asteroids.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerService.class);

    private final PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public void register(String username) throws IllegalPlayerUsernameException {
        try {
            Player player = new Player(username);
            repository.save(player);
            LOG.info("Registered player: " + username);
        } catch (IllegalPlayerUsernameException e) {
            LOG.warn("Failed to register player: " + username + " (illegal username)");
            throw e;
        }
    }
}
