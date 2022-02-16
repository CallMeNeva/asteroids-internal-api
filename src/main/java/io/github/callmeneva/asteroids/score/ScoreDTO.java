package io.github.callmeneva.asteroids.score;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ScoreDTO {

    @JsonProperty(value = "user") // FIXME: Configure ModelMapper to do this
    private String userUsername;
    private long value;
    private LocalDate date;
}
