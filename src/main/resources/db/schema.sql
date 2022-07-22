CREATE SCHEMA IF NOT EXISTS asteroids;

CREATE TABLE IF NOT EXISTS asteroids.players (
    username VARCHAR(20) CHARACTER SET 'ascii' NOT NULL, -- Username validation is done on the application side
    registered_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_players PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS asteroids.scores (
    value INT UNSIGNED NOT NULL,
    player_username VARCHAR(20) CHARACTER SET 'ascii' NOT NULL,
    published_on TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_scores PRIMARY KEY (value, player_username, published_on),
    CONSTRAINT fk_scores_players FOREIGN KEY (player_username) REFERENCES players (username)
);
