## Spring Boot HATEOAS and HAL Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, HATEOAS, HAL Explorer, Spring Boot DevTools

Hypermedia as the Engine of Application State (HATEOAS) is a way to send additional action
information with response. JSON Hypertext Application Language (HAL) is a standard for sending
data as HATEOAS.

Following is an HAL example, which sends additional links to interact with the resource:

```json
{
    "id": "1",
    "task": "Task 1"
    "dueDate": "2023-05-01",
    "_links": {
        "all-tasks": {
            "href": "http://localhost:8080/tasks"
        }
    }
}
```

**HAL Explorer** is an webapp for exploring HATEOAS graphiclly.
URL: http://localhost:8080/explorer/index.html#uri=/

Following is the most important classes for working with HATEOAS:

* org.springframework.hateoas.EntityModel - Wrapper for response
* org.springframework.hateoas.server.mvc.WebMvcLinkBuilder - Helper for building links

Assuming JDK 17 is installed, run the project with following command:

```shell
./mvnw spring-boot:run
```

```shell
$ curl http://localhost:8080/tasks/1
{
  "id" : 1,
  "task" : "Task 1",
  "dueDate" : "2022-12-03",
  "_links" : {
    "all-tasks" : {
      "href" : "http://localhost:8080/tasks"
    }
  }
}
```
