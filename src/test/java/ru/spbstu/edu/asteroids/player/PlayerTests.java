package ru.spbstu.edu.asteroids.player;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTests {

    @Nested
    class UsernameConstraints {

        // Things to ensure:
        // 1. Not null
        // 2. Not empty
        // 3. Length <= 20
        // 4. Alphanumeric chars only (no whitespace, non-ASCII allowed)

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {
                "ThisHasMoreThan20Characters",
                "this has spaces",
                "this\thas\ttabs",
                "separate\nlines",
                "with_underscores",
                "with-hyphens",
                "with+pluses",
                "\"Escaped\"Chars",
                "   "
        })
        void rejectsInvalid(String username) {
            assertThat(Player.isUsernameValid(username)).isFalse();
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "lowercase",
                "UPPERCASE",
                "TwentyCharsMixedCase",
                "WithNumbers01",
                "1234567890",
                "ЗаПределамиASCII"
        })
        void acceptsValid(String username) {
            assertThat(Player.isUsernameValid(username)).isTrue();
        }
    }
}
