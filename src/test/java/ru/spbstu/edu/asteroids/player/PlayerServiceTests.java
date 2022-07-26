package ru.spbstu.edu.asteroids.player;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
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
        @ArgumentsSource(LegalUsernameArgumentsProvider.class)
        void acceptsLegalUsername(String legalUsername) {
            assertThatNoException().isThrownBy(() -> service.register(legalUsername));
        }

        @ParameterizedTest
        @ArgumentsSource(IllegalUsernameArgumentsProvider.class)
        void rejectsIllegalUsername(String illegalUsername) {
            assertThatExceptionOfType(IllegalPlayerUsernameException.class).isThrownBy(() -> service.register(illegalUsername));
        }

        @Test
        void rejectsNullUsername() {
            assertThatNullPointerException().isThrownBy(() -> service.register(null));
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
