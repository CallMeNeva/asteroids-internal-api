package io.github.callmeneva.asteroids.score;

import org.apache.tomcat.util.security.MD5Encoder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

@RestController
@RequestMapping(value = "/scores")
public class ScoreController {

    private final ScoreService service;
    private final ModelMapper mapper;
    private final MessageDigest digest;

    @Value("${request-hash}")
    private String salt;

    @Autowired
    public ScoreController(ScoreService service, ModelMapper mapper, MessageDigest digest) {
        this.service = service;
        this.mapper = mapper;
        this.digest = digest;
    }

    @GetMapping(path = "/top/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ScoreGetDTO> top(@PathVariable long n, @RequestParam(required = false) String username) {
        List<Score> scores = (username != null) ? service.getTop(n, username) : service.getTop(n);
        return scores.stream()
                .map(score -> mapper.map(score, ScoreGetDTO.class))
                .toList();
    }

    @GetMapping(path = "/highscores", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ScoreGetDTO> highscores(@RequestParam(required = false, defaultValue = "10") long limit) {
        List<Score> scores = service.getHighscores(limit);
        return scores.stream()
                .map(score -> mapper.map(score, ScoreGetDTO.class))
                .toList();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody ScorePostDTO data, @RequestParam(value = "hash") String clientHash) {
        String token = (data.getUsername() + data.getValue() + salt);
        byte[] rawDigest = digest.digest(token.getBytes(StandardCharsets.UTF_8));
        String serverHash = MD5Encoder.encode(rawDigest);

        if (clientHash.equals(serverHash)) {
            service.save(data.getUsername(), data.getValue());
        }
    }
}
