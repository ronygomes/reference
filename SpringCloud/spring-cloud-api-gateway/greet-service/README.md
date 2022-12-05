## Spring Boot Greet Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Eureka Client, Spring Boot DevTools

This is a simple Spring Boot application with one endpoint. It will be consumed by other
microservices.

Assuming JDK 17 is installed, run the project with following command:

```shell
./mvnw spring-boot:run
```

```shell
$ curl http://localhost:8080/greet/Someone
{"message":"Hello, Someone"}
```
