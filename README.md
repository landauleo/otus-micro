###Задание:
Обернуть приложение в docker-образ и запушить его на Dockerhub
###Описание/Пошаговая инструкция выполнения домашнего задания:
1. Создать минимальный сервис, который
- отвечает на порту 8000
- имеет http-метод:
GET /health/
RESPONSE: {"status": "OK"}
2. Собрать локально образ приложения в докер
3. Запушить образ в dockerhub

На выходе необходимо предоставить
имя репозитория и тэг на Dockerhub

ссылку на github c Dockerfile, либо приложить Dockerfile в ДЗ

Обратите внимание, что при сборке на m1 при запуске вашего контейнера на стандартных платформах будет ошибка такого вида:
standard_init_linux.go:228: exec user process caused: exec format error
Для сборки рекомендую указать тип платформы linux/amd64:
docker build --platform linux/amd64 -t tag

Более подробно можно прочитать в статье: https://programmerah.com/how-to-solve-docker-run-error-standard_init_linux-go219-exec-user-process-caused-exec-format-error-39221/

## Micronaut 3.8.8 Documentation

- [User Guide](https://docs.micronaut.io/3.8.8/guide/index.html)
- [API Reference](https://docs.micronaut.io/3.8.8/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/3.8.8/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
## Feature http-client documentation

- [Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)


