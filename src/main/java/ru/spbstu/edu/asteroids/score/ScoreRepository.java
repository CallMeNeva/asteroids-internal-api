package ru.spbstu.edu.asteroids.score;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    @Query(value = "CALL global_top_n(?1)", nativeQuery = true)
    List<Score> findTopN(long n);

    @Query(value = "CALL user_top_n(?1, ?2)", nativeQuery = true)
    List<Score> findTopNByUsername(long n, String username);

    @Query(value = "CALL highscores(?1)", nativeQuery = true)
    List<Score> findHighscores(long limit);

    @Modifying
    @Query(value = "CALL insert_score_by_username(?1, ?2)", nativeQuery = true)
    void save(String username, long value);
}
