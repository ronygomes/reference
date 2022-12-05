## Spring Cloud API Gateway Reference

Spring API Gateway is the frontend for the client/consumer request. It does cross cutting concerns
like authentication, logging and hides the internal servers.

This project contain 4 parts
* **eureka-server:** Spring Cloud Service Discovery and Registration Server
* **greet-service:** Basic Spring Boot Application used as Service Provider
* **api-gateway-01:** Eureka Client and API Gateway Server with discovery locator Enabled
* **api-gateway-02:** Eureka Client and API Gateway Server with Custom Routing

Assuming JDK 17 is installed, run the project with following command:

```shell
# Run eureka-server on port 8761
$ cd eureka-server/
$ ./mvnw spring-boot:run

# Run greet-service on port 8080
$ cd greet-service/
$ ./mvnw spring-boot:run

# Run api-gateway-01 on port 8765
$ cd api-gateway-01/
$ ./mvnw spring-boot:run

# Run api-gateway-02 on port 8766
$ cd api-gateway-02/
$ ./mvnw spring-boot:run
```

Run the project using curl:
```shell
# api-gateway-01 will invoke '/greet/{name}' endpoint of 'greet-service'
# Format: <server>:<port>/<app-name>/<url>
$ curl http://localhost:8765/greet-service/greet/Java
{"greetMessage":"Hello, Java"}
```
