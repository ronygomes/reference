## Spring Cloud Resilience4j Reference

In microservice architecture it is very common for one microservice to call other
microservices. But other microservices maybe not ready yet. This library helps
to build resilient application with various well known patterns


Using resilient4j is very easy. Just add specific annotation in the request method and library
will handle the rest. It is mandatory to have `spring-boot-starter-aop` in the dependency.

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Resilient4j, AOP, DevTools


Assuming JDK 17 is installed, run the project with following command:

```shell
$ ./mvnw spring-boot:run
```

### Annotations

Each annotations takes configuration names as `name` property. By default a configuration
named `default` is created.

* **@Retry** - It is possible to retry multiple times before failing the request

* **@RateLimiter** - Can restrict certain number of request per unit of time

* **@CircuitBreaker** - It will send failureRateThreshold amount of requests to main source. If
it crossed for certain time will prevent new request from calling main source.

* **@Bulkhead** - Controls concurrent request count
