package ru.spbstu.edu.asteroids.player;

public class IllegalPlayerUsernameException extends IllegalArgumentException {

    public IllegalPlayerUsernameException(String message) {
        super(message);
    }

    public static IllegalPlayerUsernameException forUsername(String username) {
        return new IllegalPlayerUsernameException("Illegal username: " + username);
    }
}
