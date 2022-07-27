package ru.spbstu.edu.asteroids.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "players")
public class Player {

    private static final byte USERNAME_MAX_LENGTH = 20;

    @Id
    @Column(name = "username", length = USERNAME_MAX_LENGTH, nullable = false)
    private String username;

    public Player(String username) throws IllegalPlayerUsernameException {
        Objects.requireNonNull(username);
        if (username.length() > USERNAME_MAX_LENGTH || username.isBlank()) {
            throw new IllegalPlayerUsernameException();
        }

        this.username = username;
    }

    protected Player() {
        // JPA spec requirement
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Player player = (Player) obj;
        return Objects.equals(username, player.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String toString() {
        return username;
    }
}
