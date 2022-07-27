CREATE SCHEMA IF NOT EXISTS asteroids;

CREATE TABLE IF NOT EXISTS asteroids.players (
    username NVARCHAR(20) NOT NULL, -- Username validation is done on the application side

    CONSTRAINT pk_players PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS asteroids.scores (
    value INT UNSIGNED NOT NULL DEFAULT 0,
    player_username NVARCHAR(20) NOT NULL,
    achieved_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_scores PRIMARY KEY (player_username, achieved_at),
    CONSTRAINT fk_scores_players FOREIGN KEY (player_username) REFERENCES players (username)
);
