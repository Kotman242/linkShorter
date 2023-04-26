# Url Shortener
## Краткое описание
REST-сервис сокращения ссылок на Java. Используемые фреймворки и библиотеки:
- Spring (Boot, Data, Test);
- Hibernate;
- Liquibase;
- Lombok;
- JUnit и Mockito;
- PostgreSQL;
- Docker;


Запуск проекта:
<pre><code>$ java -jar ./target/linkShorter-1.0.jar</code></pre>

## Конфигурация
Конфигурации проекта в resources/application.properties
shortLink.saveLink=30  длительность хранения ссылки в днях
shortLink.domain=localhost:5500   домен для короткой ссылки

<pre><code>token.length=5    длина токена короткой ссылки

token.hasNumbers=true   содержит ли короткая ссылка цифры

token.hasLetters=true    содержит ли короткая ссылка буквы</code></pre>


## Краткое описание классов
В корневом пакете проекта `com.example.linkshorter` находится класс `LinkShorterApplication`, содержащий в себе точку входа в приложение.

В пакете `controller` содержатся класс:
- `LinksShorterController` - контроллер;
  
В пакете `service` содержатся классы:
- `LinkService` - сервис, реализующий основную логику данной программы;
  

В пакете `repository` содержится интерфейс хранилища данных `ShortLinkRepository`, логика которого имплементируется автоматически с помощью библиотеки Spring Data JPA.

В пакете model содержатся:
- `Link` - класс-модель данных, хранящихся в ShortLinkRepository;
- `LinkTO` - DTO для Link
  
В пакете `advice` содержится статический класс `ShortLinkExceptionHandlerAdvice`, осуществляющий работу с исключениями. 

В пакете `verification` содержится статический классы 
- `CheckerOldLink`, отвечающий за удаления старых ссылок. ShortLinkMaker отвечающие за проверку уникальности коротких ссылок. 
- `Generator` генерирует токен для коротких ссылок.


## Описание внешнего программного интерфейса
### Генерация коротких ссылок

Для создания новой короткой ссылки необходимо послать POST-запрос на адрес `/generate`. В теле запроса дожен находиться объект в формате JSON со строковым параметром `link`.

Этот параметр дожен удовлетворять следующим требованиям:
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
