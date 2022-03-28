package io.github.callmeneva.asteroids.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameTooLongException extends IllegalArgumentException {

    public UsernameTooLongException(String username) {
        super("Username \"" + username + "\" is too long (" + username.length() + " chars)");
    }
}
