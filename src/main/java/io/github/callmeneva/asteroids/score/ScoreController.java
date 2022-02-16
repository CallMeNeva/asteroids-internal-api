package io.github.callmeneva.asteroids.score;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/scores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScoreController {

    private final ScoreRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ScoreController(ScoreRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping(path = "/top/{n}")
    public List<ScoreDTO> top(@PathVariable long n, @RequestParam(name = "user", required = false) String username) {
        List<Score> scores = (username != null) ? repository.findTopNByUsername(n, username) : repository.findTopN(n);
        return scores.stream()
                .map(score -> mapper.map(score, ScoreDTO.class))
                .toList();
    }
}
