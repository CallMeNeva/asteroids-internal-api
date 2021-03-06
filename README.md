## `2.0.0-dev`
Please note that the `master` branch is currently outdated and all development is done [here](https://github.com/CallMeNeva/asteroids-scores-api/tree/2.0.0-dev).

## Краткое описание
Данная работа является частью группового проекта по практическому курсу ТРКПО ИКНТ ВШПИ СПбПУ.

## Схема БД
![Схема БД](asteroids_db.png)

## Сборка и запуск
Для сборки проекта требуется установленный JDK 17-й версии с настроенным `JAVA_HOME`. Как только это требование удовлетворено, достаточно запустить
обёртку Maven следующим образом:
```shell
./mvnw package
```
или
```shell
mvnw.cmd package
```
если сборка происходит на Microsoft Windows. По окончанию сборки в директории `target/` будет лежать исполняемый `jar`. При запуске приложения
требуется указать логин и пароль БД, что можно сделать с помощью параметров CLI `--db-username` и `--db-password` следующим образом:
```shell
--db-username=admin --db-password=admin
```
Другие параметры см. в `application.properties`.
