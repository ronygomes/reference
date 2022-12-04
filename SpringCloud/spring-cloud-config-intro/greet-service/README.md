## Spring Cloud Config Server Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Config Client, Spring Boot DevTools


Application naming is very important. This microservice is named `greet-service`.
It will look for remote configuration in `greet-service.properties`. 

```
spring.application.name=greet-service
spring.config.import=optional:configserver:http://localhost:8888

# Local Config, will be used if can't connect to config-server
greet-service.welcome-message=Welcome from greet-service
```

Configure profile with any of these properties
```
spring.profiles.active=dev
spring.cloud.config.profile=dev
```

Assuming JDK 17 is installed, run the project with following commnd:

```shell
./mvnw spring-boot:run
```

```shell
# If config server is up
$ curl http://localhost:8080/greet
{"message":"Greeting from Global Configuration"}


# If config server is down
$ curl http://localhost:8080/greet
{"message":"Welcome from greet-service"}

```
