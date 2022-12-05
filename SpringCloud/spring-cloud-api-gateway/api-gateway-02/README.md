## Spring Cloud Dashboard Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Eureka Client, API Gateway, Spring Boot DevTools

This project demonstrates url routing and cross cutting concern. It runs on port `8765` 
for following configuration:
```
server.port=8766
```

* `ApiGatewayConfig.java` - Custom route definition

* `LogGlobalFilter.java` - Global filter which will invoked with all request

Assuming JDK 17 is installed, run the project with following command:

```shell
./mvnw spring-boot:run
```
