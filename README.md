# Лабораторная работа #1

## Continuous Integration & Continuous Delivery

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
* Деплой на Heroku реализовать средствами GitHub Actions, для деплоя использовать docker. Для деплоя _нельзя_
  использовать Heroku CLI или webhooks.
* Приложение должно использовать БД для хранения записей.
* После успешного деплоя на Heroku, через newman запускаются интеграционные тесты. Интеграционные тесты можно проверить
  локально, для этого нужно импортировать в Postman
  коллекцию [lab1.postman_collection.json](postman/%5Binst%5D%20Lab1.postman_collection.json)]) и
  environment [[local] lab1.postman_environment.json](postman/%5Binst%5D%5Blocal%5D%20Lab1.postman_environment.json).
* В [[inst][heroku] Lab1.postman_environment.json](postman/%5Binst%5D%5Bheroku%5D%20Lab1.postman_environment.json)
  заменить значение `baseUrl`
  на адрес развернутого сервиса на heroku.

### Пояснения

* [Пример](https://github.com/Romanow/person-service) приложения на Kotlin / Spring.
* Для локальной разработки можно использовать Postgres в docker, для этого нужно запустить `docker compose up -d`,
  поднимется контейнер с Postgres 13, будет создана БД persons и пользователь `program:test`.
* Для сборки используется [GitHub Actions](https://docs.github.com/en/actions).
* Для поиска нужного инструмента для сборки используется [Github Marketplace](https://github.com/marketplace).
* Пояснение как работает [Heroku](https://devcenter.heroku.com/articles/how-heroku-works).
* Для подключения БД на Heroku заходите через Dashboard в раздел Resources и в блоке Add-ons ищете Heroku Postgres. Для
  получения адреса, пользователя и пароля переходите в саму БД и выбираете раздел Settings -> Database Credentials.

### Прием задания

1. При получении задания у вас создается fork этого репозитория для вашего пользователя.
1. Для выполнения задания вы создаете ветку develop и всю работу выполняете в ней. После того как вы полностью сделали
   задание, _все_ тесты и сценарии postman проходят _локально_ успешно, вы сливаете все изменения в master и создаете
   Pull Request из ветки master в главный репозиторий.
1. Для первичного запуска Github Actions нужно в вашем репозитории перейти во вкладку Actions и нажать `allow actions`.
1. По-умолчанию, Github Actions стартуют для ветки master и Pull Request (это описано
   в [build.yaml](.github/workflows/build.yml)), это сделано потому что количество бесплатных минут в
   организации `bmstu-rsoi` ограничено и после превышения лимита минут, сборки у всех будут падать. 