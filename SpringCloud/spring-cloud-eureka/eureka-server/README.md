## Spring Boot Greet Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Eureka Server

Eureka is a service registration and discovery server. This project will act
and the Server. Other client microservice will register and query with this server.

`@EnableEurekaServer` is added in the class containing `main()`.

Following configuration is required for preventing self registration:

```
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

Assuming JDK 17 is installed, run the project with following command:

This project will run on port `8761`.
```shell
./mvnw spring-boot:run
```
