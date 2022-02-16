package io.github.callmeneva.asteroids.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScoreService {

    private final ScoreRepository repository;

    @Autowired
    public ScoreService(ScoreRepository repository) {
        this.repository = repository;
    }

    public List<Score> getTop(long n) {
        return repository.findTopN(n);
    }

    public List<Score> getTop(long n, String username) {
        return repository.findTopNByUsername(n, username);
    }

    public List<Score> getHighscores() {
        return repository.findHighscores();
    }

    @Transactional
    public void save(String username, long value) {
        repository.save(username, value);
    }
}
