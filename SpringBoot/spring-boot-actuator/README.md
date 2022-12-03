## Spring Boot Actuator Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Actuator, Spring Boot DevTools

Spring Boot Actuator exposes various endpoint for fetching application information like health, 
beans info etc.

By default only health information is enabled. Following configurations enabed all endpoints:

```
management.endpoints.web.exposure.include=*
```

Assuming JDK 17 is installed, run the project with following commnd:

```shell
./mvnw spring-boot:run
```

Actuator index page is 'http://localhost:8080/actuator'. This page prints following JSON with
all enabled endpoints:

```json
{
   "_links":{
      "self":{
         "href":"http://localhost:8080/actuator",
         "templated":false
      },
      "beans":{
         "href":"http://localhost:8080/actuator/beans",
         "templated":false
      },
      "caches-cache":{
         "href":"http://localhost:8080/actuator/caches/{cache}",
         "templated":true
      },
      "caches":{
         "href":"http://localhost:8080/actuator/caches",
         "templated":false
      },
      "health":{
         "href":"http://localhost:8080/actuator/health",
         "templated":false
      },
      "health-path":{
         "href":"http://localhost:8080/actuator/health/{*path}",
         "templated":true
      },
      "info":{
         "href":"http://localhost:8080/actuator/info",
         "templated":false
      },
      "conditions":{
         "href":"http://localhost:8080/actuator/conditions",
         "templated":false
      },
      "configprops-prefix":{
         "href":"http://localhost:8080/actuator/configprops/{prefix}",
         "templated":true
      },
      "configprops":{
         "href":"http://localhost:8080/actuator/configprops",
         "templated":false
      },
      "env":{
         "href":"http://localhost:8080/actuator/env",
         "templated":false
      },
      "env-toMatch":{
         "href":"http://localhost:8080/actuator/env/{toMatch}",
         "templated":true
      },
      "loggers":{
         "href":"http://localhost:8080/actuator/loggers",
         "templated":false
      },
      "loggers-name":{
         "href":"http://localhost:8080/actuator/loggers/{name}",
         "templated":true
      },
      "heapdump":{
         "href":"http://localhost:8080/actuator/heapdump",
         "templated":false
      },
      "threaddump":{
         "href":"http://localhost:8080/actuator/threaddump",
         "templated":false
      },
      "metrics":{
         "href":"http://localhost:8080/actuator/metrics",
         "templated":false
      },
      "metrics-requiredMetricName":{
         "href":"http://localhost:8080/actuator/metrics/{requiredMetricName}",
         "templated":true
      },
      "scheduledtasks":{
         "href":"http://localhost:8080/actuator/scheduledtasks",
         "templated":false
      },
      "mappings":{
         "href":"http://localhost:8080/actuator/mappings",
         "templated":false
      }
   }
}
```

```shell
$ curl http://localhost:8080/actuator/health
{"status":"UP"}
```
