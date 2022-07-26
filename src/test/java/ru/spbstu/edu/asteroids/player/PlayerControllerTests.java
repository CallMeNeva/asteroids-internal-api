package ru.spbstu.edu.asteroids.player;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PlayerService service;

    @Nested
    class Registration {

        @ParameterizedTest
        @ArgumentsSource(LegalUsernameArgumentsProvider.class)
        void legalUsernameReturns201(String legalUsername) throws Exception {
            assertReturnsExpectedCode(legalUsername, 201);
        }

        @ParameterizedTest
        @ArgumentsSource(IllegalUsernameArgumentsProvider.class)
        void illegalUsernameReturns422(String illegalUsername) throws Exception {
            assertReturnsExpectedCode(illegalUsername, 422);
        }

        @Test
        void noUsernameReturns400() throws Exception {
            assertReturnsExpectedCode(null, 400);
        }

        void assertReturnsExpectedCode(String username, int statusCode) throws Exception {
            mockMvc.perform(post("/players")
                    .param("username", username))
                    .andExpect(status().is(statusCode));
        }
    }
}
