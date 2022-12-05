## Spring Cloud OpenFeign Reference

OpenFeign provides an easy way to fetch data from other Microservices.

This project contain 2 parts
* **greet-service:** Basic Spring Boot Application used as Service Provider
* **dashboard-service:** Consumes API from **greet-service** using RestTemplate and OpenFeign

Assuming JDK 17 is installed, run the project with following command:

```shell
# Run greet-service on port 8080
$ cd greet-service/
$ ./mvnw spring-boot:run

# Run dashboard-service on port 8081
$ cd dashboard-service/
$ ./mvnw spring-boot:run
```

Run the project using curl:
```shell
# Uses RestTemplate
$ curl http://localhost:8081/greetRest/Java
{"greetMessage":"Hello, Java"}

# Uses Open Feign
$ curl http://localhost:8081/greet/Java
{"greetMessage":"Hello, Java"}
```
