## Spring Boot Locale and Message Format

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Spring Boot DevTools

Assuing JDK 17 is installed, run the project with following commnd:

```shell
./mvnw spring-boot:run
```

It is recommended to introduce new features using version. Following are some way to maintain
versioning

* URL Versioning: /v1/tasks, /v2/tasks
* Request Parameter Versioning: /tasks?version=1, /tasks?version=2
* Custom Header Versioning: X-API-Version: 1, X-API-Version: 2
* Custom Mime Type Versioning: Accept: application/vnd.ronygomes.me-v1+json, Accept: application/vnd.ronygomes.me-v2+json

```shell$ 
$ curl http://localhost:8080/v1/greetUrl
{"message":"Hello World!"}

$ curl http://localhost:8080/v2/greetUrl
{"greeting":"World, Hello"}

$ curl "http://localhost:8080/greetRequestParam?version=1"
{"message":"Hello World!"}

$ curl "http://localhost:8080/greetRequestParam?version=2"
{"greeting":"World, Hello"}

$ curl -H 'X-API-Version: 1' http://localhost:8080/greetCustomHeader
{"message":"Hello World!"}

$ curl -H 'X-API-Version: 2' http://localhost:8080/greetCustomHeader
{"greeting":"World, Hello"}

$ curl -H 'Accept: application/vnd.ronygomes.me-v1+json' http://localhost:8080/greetCustomMimeType
{"message":"Hello World!"}

$ curl -H 'Accept: application/vnd.ronygomes.me-v2+json' http://localhost:8080/greetCustomMimeType
{"greeting":"World, Hello"}
```