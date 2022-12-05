## Spring Cloud Config Server Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Open Feign, Spring Boot DevTools

This application is a Feign Client. It will invoke `/greet/{name}` endpoint of 
`greet-service`.

This project runs on port `8081` for following configuration:
```
server.port=8081
```

`@EnableFeignClients` is added in class containing `main()`.

Assuming JDK 17 is installed, run the project with following command:

```shell
./mvnw spring-boot:run
```

```shell
$ curl http://localhost:8081/greet/Someone
{"greetMessage":"Hello, Someone"}
```
