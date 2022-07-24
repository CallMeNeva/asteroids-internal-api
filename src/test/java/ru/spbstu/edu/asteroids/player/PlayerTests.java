package ru.spbstu.edu.asteroids.player;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

class PlayerTests {

    @Nested
    class UsernameConstraints {

        // Things to ensure:
        // 1. Not null
        // 2. Not blank
        // 3. Length <= 20

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"   ", "\t", "\t \n", "ThisHasMoreThan20Characters"})
        void rejectsIllegal(String username) {
            assertThatExceptionOfType(IllegalPlayerUsernameException.class).isThrownBy(() -> new Player(username));
        }

        @ParameterizedTest
        @ValueSource(strings = {"lower", "UPPER", "TwentyCharsMixedCase", "1234567890", "ЗаПределамиASCII", "with whitespace"})
        void acceptsLegal(String username) {
            assertThatNoException().isThrownBy(() -> new Player(username));
        }
    }

    @Nested
    class EqualsAndHashCode {
        // TODO
    }
}
