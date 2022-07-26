package ru.spbstu.edu.asteroids.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService service;

    @Autowired
    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestParam String username) {
        if (username == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            service.register(username);
            return ResponseEntity.status(CREATED).build();
        } catch (IllegalPlayerUsernameException e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
