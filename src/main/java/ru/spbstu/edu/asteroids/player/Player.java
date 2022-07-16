package ru.spbstu.edu.asteroids.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.spbstu.edu.asteroids.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player extends BaseEntity<Integer> {

    private static final byte USERNAME_MAX_LENGTH = 20;

    @Column(name = "username", length = USERNAME_MAX_LENGTH, nullable = false, unique = true)
    private String username;

    public static boolean isUsernameValid(String username) {
        return (username != null) && (username.length() <= USERNAME_MAX_LENGTH) && !username.isBlank();
    }

    // TODO: equals and hashCode

    @Override
    public String toString() {
        return username;
    }
}
