# SoftTest

Код находится на ветке master

Для запуска кода нужно открыть папку deploy. Там лежит свежий jar файл проекта и docker-compose.

**Запуск через Docker Compose**
Требования:
Установлены Docker и Docker Compose.
Файл .jar лежит в папке ./deploy. (в проекта сейчас так и есть)

Запуск контейнера: docker-compose up

**Запуск через JAR-файл (локально)**
Требования:
Установлена Java 21 (или версия, на которой собрано приложение).
Файл .jar лежит в папке ./deploy. (в проекта сейчас так и есть)

Запуск java -jar ./deploy/имя.jar
