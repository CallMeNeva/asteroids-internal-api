package ru.spbstu.edu.asteroids.player;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

class LegalUsernameArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of("lower"),
                Arguments.of("UPPER"),
                Arguments.of("TwentyCharsMixedCase"),
                Arguments.of("1234567890"),
                Arguments.of("ЗаПределамиASCII"),
                Arguments.of("with whitespace")
        );
    }
}
