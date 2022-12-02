## Spring Boot Locale and Message Format

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Spring Boot DevTools

Assuing JDK 17 is installed, run the project with following commnd:

```shell
./mvnw spring-boot:run
```

Following dependency is needed for 'application/xml' content type support:

```xml
<dependency>
   <groupId>com.fasterxml.jackson.dataformat</groupId>
   <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

Use `Accept` and `Accept-Language` HTTP header for fetching format and language specific response:

```shell$ 
curl http://localhost:8080/greet
{"message":"Hello World!"}

$ curl -H 'Accept: application/xml' http://localhost:8080/greet
<Map><message>HelloWorld!<message><Map>                                                           

$ curl -H 'Accept-Language: bn' http://localhost:8080/greet
{"message":"হ্যালো পৃথিবী!"}
```