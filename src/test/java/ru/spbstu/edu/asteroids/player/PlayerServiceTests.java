package ru.spbstu.edu.asteroids.player;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assumptions.assumeThatCode;

@SpringBootTest
class PlayerServiceTests {

    @Autowired
    PlayerService service;

    @Autowired
    PlayerRepository repository;

    @Nested
    class Registration {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {"   ", "\t", "\t \n", "ThisHasMoreThan20Characters"})
        void rejectsIllegalUsername(String illegalUsername) {
            assertThatExceptionOfType(IllegalPlayerUsernameException.class).isThrownBy(() -> service.register(illegalUsername));
        }

        @ParameterizedTest
        @ValueSource(strings = {"lower", "UPPER", "TwentyCharsMixedCase", "1234567890", "ЗаПределамиASCII", "with whitespace"})
        void acceptsLegalUsername(String legalUsername) {
            assertThatNoException().isThrownBy(() -> service.register(legalUsername));
        }

        @Test
        void persistsToDatabase() {
            boolean emptyBeforeRegistration = (repository.count() == 0);
            assumeThatCode(() -> service.register("HelloWorld")).doesNotThrowAnyException();
            boolean notEmptyAfterRegistration = (repository.count() > 0);

            assertThat(emptyBeforeRegistration && notEmptyAfterRegistration).isTrue();
        }
    }
}
