package ru.spbstu.edu.asteroids.player;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

class IllegalUsernameArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("   "),
                Arguments.of("\t"),
                Arguments.of("\t \n"),
                Arguments.of("ThisHasMoreThan20Characters")
        );
    }
}
