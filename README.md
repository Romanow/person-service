# Person Service

[![Build project](https://github.com/Romanow/person-service/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/Romanow/person-service/actions/workflows/build.yml)

### Формулировка

В рамках первой лабораторной работы требуется написать простейшее веб приложение, предоставляющее пользователю набор
операций над сущностью Person. Для этого приложения автоматизировать процесс сборки, тестирования и релиза на Heroku.

Приложение должно реализовать API:

* `GET /persons/{personId}` – информация о человеке;
* `GET /persons` – информация по всем людям;
* `POST /persons` – создание новой записи о человеке;
* `PATCH /persons/{personId}` – обновление существующей записи о человеке;
* `DELETE /person/{personId}` – удаление записи о человеке.

[Описание API](person-service.yaml) в формате OpenAPI.

### Требования

* Исходный проект хранится на Github. Для сборки использовать только Github actions.
* Сборку, запуск тестов и деплой реализовать отдельными шагами.
* Запросы / ответы должны быть в формате JSON.
* Если запись по id не найдена, то возвращать HTTP статус 404 Not Found.
* При создании новой записи о человека (метод POST /person) возвращать HTTP статус 201 Created с пустым телом и
  Header `Location: /api/v1/persons/{personId}`, где personId – id созданной записи.
* Приложение должно содержать 4-5 unit-тестов на реализованные операции.
* Приложение должно быть завернуто в docker.
* Приложение должно использовать базу данных для хранения записей.
* В [build.yml](.github/workflows/build.yml) дописать шаги на сборку, и прогон unit-тестов.

### Пояснения

* [Пример](https://github.com/Romanow/person-service) приложения на Kotlin / Spring.
* Для локальной разработки можно использовать Postgres в docker, для этого нужно
  запустить `docker compose up -d postgres`, поднимется контейнер с Postgres 13, будет создана БД `persons` и
  пользователь `program:test`.
* Для сборки используется [GitHub Actions](https://docs.github.com/en/actions).

### Локальный запуск кластера k8s с использованием kind

```shell
$ kind create cluster --config kind.yml
Creating cluster "kind" ...
 ✓ Ensuring node image (kindest/node:v1.25.2) 🖼
 ✓ Preparing nodes 📦
 ✓ Writing configuration 📜
 ✓ Starting control-plane 🕹️
 ✓ Installing CNI 🔌
 ✓ Installing StorageClass 💾
Set kubectl context to "kind-kind"

$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml
$ kubectl wait --namespace ingress-nginx \
    --for=condition=ready pod \
    --selector=app.kubernetes.io/component=controller \
    --timeout=90s

$ echo "127.0.0.1    person-service.local" | sudo tee -a /etc/hosts

$ skaffold dev

$ curl http://person-service.local/manage/health
```
