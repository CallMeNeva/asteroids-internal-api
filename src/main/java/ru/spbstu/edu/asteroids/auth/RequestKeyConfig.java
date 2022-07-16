package ru.spbstu.edu.asteroids.auth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@NoArgsConstructor
@Configuration
@PropertySource("classpath:/auth/auth.properties")
public class RequestKeyConfig {

    @Value("${salt}")
    private String salt;

    @Value("${delimiter}")
    private String delimiter;
}
