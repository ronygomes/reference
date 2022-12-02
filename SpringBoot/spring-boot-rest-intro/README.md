## Spring Boot Hello World Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Spring Boot DevTools

Note: DevTools includes Stack Trace in error page. But when application is run using `java -jar` (prod)
DevTools is automatically disabled.

Default logging level is INFO, but DEBUG will show Auto Configuration logs. Add following line in `resources/application.properties`

```
logging.level.org.springframework=DEBUG
```

Assuing JDK 17 is installed, run the project with following commnd:

```shell
./mvnw spring-boot:run
```

```shell
$ curl http://localhost:8080/hello-world-text
Hello World!

$ curl http://localhost:8080/hello-world-map
{"message":"Hello World!"}

$ curl http://localhost:8080/hello-world-pojo
{"message":"Hello World!"}  

$ curl http://localhost:8080/tasks
[{"id":1,"task":"Task 1","dueDate":"2022-12-01"},{"id":2,"task":"Task 2","dueDate":"2022-12-01"},{"id":3,"task":"Task 3","dueDate":"2022-12-01"}]

$ curl http://localhost:8080/tasks/1
{"id":1,"task":"Task 1","dueDate":"2022-12-01"}

$ curl -X POST -H 'Content-Type: application/json' -d '{"task":"Hello World","dueDate":"2022-12-03"}' http://localhost:8080/tasks

$ curl -X DELETE http://localhost:8080/tasks/1

$ curl http://localhost:8080/tasks/101

{"timestamp":"2022-12-01T17:32:30.480+00:00","status":404,"error":"Not Found",
"trace":"<Stack Trace>","message":"Unable to find Task with id: 101","path":"/tasks/101"}
```

## Class Details
[Application.java][1] - `main()` class for this Spring Boot Application

[HelloWorldController.java][2] - Basic controller which prints Hello World!

[TaskResource.java][3] - Restful Task Create/Read/Delete. It is convention to use 'Resource' as suffix 

[1]: https://github.com/ronygomes/reference/blob/master/SpringBoot/spring-boot-rest-intro/src/main/java/me/ronygomes/reference/springboot/Application.java
[2]: https://github.com/ronygomes/reference/blob/master/SpringBoot/spring-boot-rest-intro/src/main/java/me/ronygomes/reference/springboot/HelloWorldController.java
[3]: https://github.com/ronygomes/reference/blob/master/SpringBoot/spring-boot-rest-intro/src/main/java/me/ronygomes/reference/springboot/TaskResource.java