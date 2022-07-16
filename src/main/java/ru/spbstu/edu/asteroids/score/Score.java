package ru.spbstu.edu.asteroids.score;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.spbstu.edu.asteroids.model.BaseEntity;
import ru.spbstu.edu.asteroids.player.Player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "scores")
public class Score extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "value", nullable = false)
    private long value;

    // TODO: equals and hashCode

    @Override
    public String toString() {
        return Long.toString(value);
    }
}
