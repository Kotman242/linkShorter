# Url Shortener
## Краткое описание
REST-сервис сокращения ссылок на Java. Используемые фреймворки и библиотеки:
- Spring (Boot, Data, Test,Security);
- Hibernate;
- Liquibase;
- Lombok;
- JUnit и Mockito;
- PostgreSQL;
- Docker;


Запуск проекта:
<pre><code>$ java -jar ./target/linkShorter-1.0.jar</code></pre>

## Тест аккаунт:

Login: user1
Password: 11111

## Конфигурация
Конфигурации проекта в resources/application.properties
shortLink.saveLink=30  длительность хранения ссылки в днях
shortLink.domain=localhost:5500   домен для короткой ссылки

<pre><code>token.length=5    длина токена короткой ссылки

token.hasNumbers=true   содержит ли короткая ссылка цифры

token.hasLetters=true    содержит ли короткая ссылка буквы</code></pre>


## Описание внешнего программного интерфейса
### Генерация коротких ссылок

Для доступа к главной странице необходимо послать GET- запрос на адрес "/".
Доступ к странице регистрации необходимо послать GET- запрос на адрес "/registration".
Для создания новой короткой ссылки необходимо послать POST-запрос на адрес  /generate . В теле запроса должен находиться объект в формате JSON со строковым параметром  link .
Получения Спика коротких ссылок конкретного пользователя необходимо послать POST-запрос на адрес  /all . Пользователь должен быть залогинен .
Для перехода на оригинальную ссылку необходимо послать GET запрос на адрес "/get/{link}".

Этот параметр должен удовлетворять следующим требованиям:
- не пустая строка;
- строка должна соответствовать формату URL с обязательным указанием протокола.
  В ответе возвращается объект с единственным полем `link`, в котором указывается сгенерированная короткая ссылка.

Пример запроса:

<code><pre>POST /generate HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
"link": "https://some-server.com/some/url?some_param=1"
}
Пример ответа:
HTTP/1.1 200 OK
Content-Type: application/json

{
"link": "https://domen-from_application.properties/someShortLink"
}</code></pre>


### Переход по короткой ссылке
Для перехода по короткой ссылке необходимо осуществить GET-запрос по ссылке, возвращенной на шаге генерации.
<code><pre>GET /someShortLink HTTP/1.1</code></pre>
