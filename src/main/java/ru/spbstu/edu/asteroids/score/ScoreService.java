package ru.spbstu.edu.asteroids.score;

import ru.spbstu.edu.asteroids.RequestKeyVerifier;
import ru.spbstu.edu.asteroids.user.User;
import ru.spbstu.edu.asteroids.user.IllegalUsernameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScoreService {

    private final ScoreRepository repository;
    private final RequestKeyVerifier keyVerifier;

    @Autowired
    public ScoreService(ScoreRepository repository, RequestKeyVerifier keyVerifier) {
        this.repository = repository;
        this.keyVerifier = keyVerifier;
    }

    public List<Score> getTop(long n) {
        return repository.findTopN(n);
    }

    public List<Score> getTop(long n, String username) {
        return repository.findTopNByUsername(n, username);
    }

    public List<Score> getHighscores(long limit) {
        return repository.findHighscores(limit);
    }

    @Transactional
    public void save(String username, long value, String key) {
        if (!User.isUsernameValid(username)) {
            throw new IllegalUsernameException(username);
        }

        if (keyVerifier.verify(key, username, value)) {
            repository.save(username, value);
        }
    }
}
