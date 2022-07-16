package ru.spbstu.edu.asteroids.score;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ScoreGetDTO {

    private String username;
    private long value;
    private LocalDate date;
}
