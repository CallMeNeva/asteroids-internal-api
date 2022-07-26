package ru.spbstu.edu.asteroids.player;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class PlayerTests {

    @Nested
    class UsernameConstraints {

        @ParameterizedTest
        @ArgumentsSource(LegalUsernameArgumentsProvider.class)
        void acceptsLegal(String legalUsername) {
            assertThatNoException().isThrownBy(() -> new Player(legalUsername));
        }

        @ParameterizedTest
        @ArgumentsSource(IllegalUsernameArgumentsProvider.class)
        void rejectsIllegal(String illegalUsername) {
            assertThatExceptionOfType(IllegalPlayerUsernameException.class).isThrownBy(() -> new Player(illegalUsername));
        }

        @Test
        void rejectsNull() {
            assertThatNullPointerException().isThrownBy(() -> new Player(null));
        }
    }

    @Nested
    class EqualsAndHashCode {
        // TODO
    }
}
