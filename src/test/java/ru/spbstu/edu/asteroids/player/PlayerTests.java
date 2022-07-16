package ru.spbstu.edu.asteroids.player;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTests {

    @ParameterizedTest
    @ValueSource(strings = {"AzureDiamond", "exactly20characters!", "with some spaces", "ДругойЯзык"})
    void acceptsValidUsernames(String username) {
        assertThat(Player.isUsernameValid(username)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "\t", "\t\n", "definitelyMoreThan20Characters"})
    @NullAndEmptySource
    void rejectsInvalidUsernames(String username) {
        assertThat(Player.isUsernameValid(username)).isFalse();
    }
}
