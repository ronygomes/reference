## Spring Boot JSON Filtering Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Spring Boot DevTools

Assuming JDK 17 is installed, run the project with following commnd:

```shell
./mvnw spring-boot:run
```

It is possible to filter JSON properties using `@JsonIgnore` (static field level filter), `@JsonIgnoreProperties` (static class level filtering)
and `@JsonFilter` (conditional filtering)

```shell
$ curl http://localhost:8080/tasks/1
{"id":1,"task":"Task 1","dueDate":"2022-12-03"}

$ curl http://localhost:8080/tasks/1?showInternal3=true
{"id":1,"task":"Task 1","dueDate":"2022-12-03","internal3":"Internal 3"}
```
