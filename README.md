[![CI](https://github.com/Romanow/person-service/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/Romanow/person-service/actions/workflows/build.yml)
[![pre-commit](https://img.shields.io/badge/pre--commit-enabled-brightgreen?logo=pre-commit)](https://github.com/pre-commit/pre-commit)
[![Release](https://img.shields.io/github/v/release/Romanow/person-service?logo=github&sort=semver)](https://github.com/Romanow/person-service/releases/latest)
[![Person Service](https://img.shields.io/docker/pulls/romanowalex/person-service?logo=docker)](https://hub.docker.com/r/romanowalex/person-service)
[![License](https://img.shields.io/github/license/Romanow/person-service)](https://github.com/Romanow/person-service/blob/main/LICENSE)

# Person Service

GitHub: [romanow/person-service](https://github.com/Romanow/person-service).

* `GET /persons/{personId}` – информация о человеке.
* `GET /persons` – информация по всем людям.
* `POST /persons` – создание новой записи о человеке.
* `PATCH /persons/{personId}` – обновление существующей записи о человеке.
* `DELETE /person/{personId}` – удаление записи о человеке.

[Описание API](person-service.yaml).

## Локальный запуск

Используем [docker-compose.yml](docker-compose.yml)

```shell
$ docker compose \
    -f docker-compose.yml \
    -f docker-compose.frontend.yml \
    up -d --wait
```
