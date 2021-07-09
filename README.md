# Лабораторная работа #1

## Continuous Integration & Continuous Delivery

### Формулировка

В рамках первой лабораторной работы требуется написать простейшее веб приложение, предоставляющее пользователю набор операций над сущностью Person. Для этого приложения автоматизировать процесс сборки, тестирования и релиза на Heroku.

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
* Запросы/ответы должны быть в формате JSON.
* Если запись по id не найдена, то возвращать HTTP статус 404 Not Found.
* При создании новой записи о человека (метод POST /person) возвращать HTTP статус 201 Created с пустым телом и
  Header "Location: https://rsoi-person-service.herokuapp.com/persons/{personId}", где personId – id созданной записи.
* Приложение должно содержать 4-5 unit-тестов на реализованные операции.
* Деплой на Heroku реализовать средствами GitHub Actions. Для деплоя нельзя использовать Heroku CLI или webhooks.
* Приложение должно использовать БД для хранения записей.
* После успешного деплоя на Heroku, через newman запускаются интеграционные тесты. Интеграционные тесты можно проверить локально,
  для этого нужно импортировать в Postman коллекцию [lab1.postman_collection.json](postman/%5Binst%5D%20Lab1.postman_collection.json)]) и
  environment [[local] lab1.postman_environment.json](postman/%5Binst%5D%5Blocal%5D%20Lab1.postman_environment.json).
* В [[inst][heroku] Lab1.postman_environment.json](postman/%5Binst%5D%5Bheroku%5D%20Lab1.postman_environment.json) заменить значение `baseUrl`
  на адрес развернутого сервиса на heroku.

### Пояснения

* Пример приложения на Kotlin/Spring https://github.com/Romanow/person-service
* Для сборки используется GitHub Actions https://docs.github.com/en/actions
* Для поиска нужного инструмента для сборки используется Github Marketplace https://github.com/marketplace
* Для понимания как работает Heroku https://devcenter.heroku.com/articles/how-heroku-works
* Для подключения БД на Heroku заходите через Dashboard в раздел Resources и в блоке Add-ons ищете Heroku Postgres. Для получения адреса, пользователя и пароля переходите в саму БД и выбираете раздел Settings -> Database Credentials.
