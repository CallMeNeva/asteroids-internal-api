package ru.spbstu.edu.asteroids.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    // TODO: Convert native queries to JPQL

    @Query(value = "SELECT * FROM asteroids.players ORDER BY created_on ASC LIMIT 1", nativeQuery = true)
    Optional<Player> findOldest();

    @Query(value = "SELECT * FROM asteroids.players ORDER BY created_on DESC LIMIT 1", nativeQuery = true)
    Optional<Player> findNewest();
}
