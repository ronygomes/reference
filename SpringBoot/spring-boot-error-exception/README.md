## Spring Boot Error Exception Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Validation, Spring Boot DevTools

Assuming JDK 17 is installed, run the project with following commnd:

```shell
./mvnw spring-boot:run
```

```shell

$ curl http://localhost:8080/tasks/101
{"timestamp":"2022-12-02T13:56:53.83874","summary":"Unable to find Task with id: 101","path":"uri=/tasks/101","errors":[]}

$ curl -X POST -H 'Content-Type: application/json' -d '{"task":"Hello World","dueDate":"2021-12-03"}' http://localhost:8080/tasks

{"timestamp":"2022-12-02T13:55:16.739223","summary":"Validation Error","path":"uri=/tasks","errors":["size must be between 2 and 10","must be a future date"]}
```

## Class Details
[Application.java][1] - `main()` class for this Spring Boot Application

[CustomizedResponseEntityExceptionHandler.java][2] - `@ControllerAdvice` to override default messages

[1]: https://github.com/ronygomes/reference/blob/master/SpringBoot/spring-boot-error-exception/src/main/java/me/ronygomes/reference/springboot/Application.java
[2]: https://github.com/ronygomes/reference/blob/master/SpringBoot/spring-boot-error-exception/src/main/java/me/ronygomes/reference/springboot/CustomizedResponseEntityExceptionHandler.java
