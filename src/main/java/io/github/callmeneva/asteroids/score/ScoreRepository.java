package io.github.callmeneva.asteroids.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    @Query(value = "SELECT * FROM scores ORDER BY score_value DESC LIMIT ?1", nativeQuery = true)
    List<Score> findTopN(long n);

    @Query(value = "SELECT * FROM scores WHERE user_id = (SELECT id FROM users WHERE username = ?2) ORDER BY score_value DESC LIMIT ?1", nativeQuery = true)
    List<Score> findTopNByUsername(long n, String username);
}
