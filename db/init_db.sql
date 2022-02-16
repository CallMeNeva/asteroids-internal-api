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
