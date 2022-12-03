## Spring Boot JPA H2 Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, JPA, H2, Spring Boot DevTools

* If H2 console is enabled it will be avaiable at http://localhost:8080/h2-console
* If `resources/data.sql` file exists, it will be executed in test database

Following configurations are added for this project:
```
# By default a random url is generated, giving this will use this one
spring.datasource.url=jdbc:h2:mem:testdb

# Otherwise data.sql will run before Hibernate schema generation
spring.jpa.defer-datasource-initialization=true

# Auto enables if DevTools exists otherwise need to set it
spring.h2.console.enabled=true

# Show generated SQL
spring.jpa.show-sql=true
```

Assuming JDK 17 is installed, run the project with following commnd:

```shell
./mvnw spring-boot:run
```

```shell
$ curl http://localhost:8080/tasks/1
{"id":1,"task":"Task DB 1","dueDate":"2022-12-03"}

$ curl -H 'Content-Type: application/json' -X POST -d '{"task":"Task CURL 4","dueDate":"2022-12-03"}' http://localhost:8080/tasks
```
