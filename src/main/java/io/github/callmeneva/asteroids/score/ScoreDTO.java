package io.github.callmeneva.asteroids.score;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ScoreDTO {

    private String username;
    private long value;
    private LocalDate date;
}
