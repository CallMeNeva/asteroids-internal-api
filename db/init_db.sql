-- ----------- --
-- Создание БД --
-- ----------- --

CREATE DATABASE asteroids;

USE asteroids;

-- --------------- --
-- Создание таблиц --
-- --------------- --

CREATE TABLE asteroids.users
(
    id       BIGINT      NOT NULL AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,

    PRIMARY KEY (id),
    UNIQUE (username),
    KEY (username)
);

CREATE TABLE asteroids.scores
(
    id          BIGINT NOT NULL AUTO_INCREMENT,
    user_id     BIGINT NOT NULL,
    score_value BIGINT NOT NULL,
    date        DATE   NOT NULL DEFAULT (CURRENT_DATE),

    PRIMARY KEY (id),
    CONSTRAINT scores_users_fk FOREIGN KEY (user_id) REFERENCES users (id)
);

-- -------------------------- --
-- Добавление тестовых данных --
-- -------------------------- --

INSERT INTO asteroids.users (username)
VALUES ('EvilDrone');
INSERT INTO asteroids.users (username)
VALUES ('Ra1nbowF');

INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'EvilDrone'), 598112, '2022-02-14');
INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'EvilDrone'), 47611, '2022-02-14');
INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'EvilDrone'), 120952, '2022-02-15');
INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'EvilDrone'), 20981, '2022-02-16');
INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'Ra1nbowF'), 126122, '2022-02-13');
INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'Ra1nbowF'), 779091, '2022-02-13');
INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'Ra1nbowF'), 598122, '2022-02-14');
INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'Ra1nbowF'), 209091, '2022-02-14');
INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'Ra1nbowF'), 5760, '2022-02-15');
INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'Ra1nbowF'), 66874, '2022-02-15');
INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'Ra1nbowF'), 998091, '2022-02-16');
INSERT INTO asteroids.scores (user_id, score_value, date)
VALUES ((SELECT id FROM users WHERE username = 'Ra1nbowF'), 15, '2022-02-16');

-- --------------
-- Создание ХП --
-- ----------- --

CREATE PROCEDURE global_top_n(IN n BIGINT)
BEGIN
    SELECT * FROM scores ORDER BY score_value DESC LIMIT n;
END;


CREATE PROCEDURE user_top_n(IN n BIGINT, IN username VARCHAR(20))
BEGIN
    SELECT * FROM scores WHERE user_id = (SELECT id FROM users WHERE users.username = username) ORDER BY score_value DESC LIMIT n;
END;


CREATE PROCEDURE highscores()
BEGIN
    SELECT
        id,
        user_id,
        MAX(score_value) AS 'score_value',
        date
    FROM scores GROUP BY user_id ORDER BY score_value DESC;
END;


CREATE PROCEDURE insert_score_by_username(IN username VARCHAR(20), IN value BIGINT)
BEGIN
    DECLARE found_user_id BIGINT;

    IF NOT EXISTS(SELECT * FROM users WHERE users.username = username) THEN
        INSERT INTO users (users.username) VALUES (username);
    END IF;

    SELECT id INTO found_user_id FROM users WHERE users.username = username;
    INSERT INTO scores (user_id, score_value) VALUES (found_user_id, value);
END;
