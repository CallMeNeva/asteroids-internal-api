package ru.spbstu.edu.asteroids.score;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScorePostDTO {

    private String username;
    private long value;
}
