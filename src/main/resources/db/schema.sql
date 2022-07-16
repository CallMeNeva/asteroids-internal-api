CREATE SCHEMA IF NOT EXISTS asteroids;

CREATE TABLE IF NOT EXISTS asteroids.players (
    id         SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username   NVARCHAR(20)      NOT NULL, -- Username validation is probably better off being done on the application side
    created_on TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_players     PRIMARY KEY (id),
    CONSTRAINT unique_players UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS asteroids.scores (
    id         INT      UNSIGNED NOT NULL AUTO_INCREMENT,
    player_id  SMALLINT UNSIGNED NOT NULL,
    value      INT      UNSIGNED NOT NULL,
    created_on TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_scores         PRIMARY KEY (id),
    CONSTRAINT fk_scores_players FOREIGN KEY (player_id) REFERENCES players (id)
);
