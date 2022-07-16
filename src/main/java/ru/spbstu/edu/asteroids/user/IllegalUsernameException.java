package ru.spbstu.edu.asteroids.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalUsernameException extends IllegalArgumentException {

    public IllegalUsernameException(String username) {
        super("Username \"" + username + "\" is not valid");
    }
}
