package ru.spbstu.edu.asteroids.score;

import ru.spbstu.edu.asteroids.player.Player;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "scores")
public class Score {

    @Embeddable
    @Access(AccessType.FIELD) // Explicitness just in case, since we don't need property accessors for PK
    public static class PK implements Serializable {

        @ManyToOne
        @JoinColumn(name = "player_username", nullable = false)
        private Player player;

        @Column(name = "achieved_at", nullable = false)
        private OffsetDateTime achievementDateTime;

        public PK(Player player, OffsetDateTime achievementDateTime) {
            this.player = Objects.requireNonNull(player);
            this.achievementDateTime = Objects.requireNonNull(achievementDateTime);
        }

        public PK() {
            // JPA spec requirement
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            PK pk = (PK) obj;
            return Objects.equals(player, pk.player) && Objects.equals(achievementDateTime, pk.achievementDateTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(player, achievementDateTime);
        }

        @Override
        public String toString() {
            return String.format("Score.PK[player=%s, achieved=%s]", player.getUsername(), achievementDateTime.toString());
        }
    }

    @Column(name = "value", nullable = false)
    private long value;

    @EmbeddedId
    private PK pk;

    public Score(long value, Player player, OffsetDateTime achievementDateTime) {
        this.value = value;
        this.pk = new PK(player, achievementDateTime);
    }

    public Score(long value, Player player) {
        this(value, player, OffsetDateTime.now());
    }

    protected Score() {
        // JPA spec requirement
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Player getPlayer() {
        return pk.player;
    }

    public OffsetDateTime getAchievementDateTime() {
        return pk.achievementDateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Score score = (Score) obj;
        return value == score.value && Objects.equals(pk, score.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, pk);
    }

    @Override
    public String toString() {
        Player player = getPlayer();
        return value + " by <" + player.getUsername() + "> at " + getAchievementDateTime();
    }
}
