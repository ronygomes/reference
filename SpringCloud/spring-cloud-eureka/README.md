## Spring Cloud Eureka Reference

OpenFeign provides an easy way to fetch data from other Microservices.

This project contain 2 parts
* **eureka-server:** Spring Cloud Service Discovery and Registration Server
* **greet-service:** Basic Spring Boot Application used as Service Provider
* **dashboard-service:** Consumes API from **greet-service** using RestTemplate and OpenFeign

Both of **greet-service** and **dashboard-service** registers to Eureka Server as Eureka Client dependency
is present in class path.

If multiple instances of same microservice is present in the Eureka Server, Feign Client will automatically load
balance between them using `spring-cloud-loadbalancer`.

Assuming JDK 17 is installed, run the project with following command:

```shell
# Run eureka-server on port 8761
$ cd eureka-server/
$ ./mvnw spring-boot:run

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
