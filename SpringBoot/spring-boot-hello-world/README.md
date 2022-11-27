## Spring Boot Hello World Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Spring Data JPA, H2 Datbase, Spring Boot DevTools

Assuing JDK 17 is installed, run the project with following commnd:

```shell
./mvnw spring-boot:run
```

```shell
$ curl http://localhost:8080/hello-world-text
Hello World!

$ curl http://localhost:8080/hello-world-map
{"message":"Hello World!"}

$ curl http://localhost:8080/hello-world-pojo
{"message":"Hello World!"}  
```
