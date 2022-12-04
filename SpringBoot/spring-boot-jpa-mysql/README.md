## Spring Boot JPA MySQL Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, JPA, MySQL Driver, Spring Boot DevTools

By default `resources/data.sql` file is executed only for embedded in-memory database.
Following configuration makes it to run always
```
spring.sql.init.mode=always
```

Assuming JDK 17 is installed, run the project with following command:

```shell
./mvnw spring-boot:run
```

MySQL database can be started using docker with following command:
 
```
docker run --detach --env MYSQL_USER=admin --env MYSQL_PASSWORD=12345 \
--env MYSQL_DATABASE=testdb --env MYSQL_ROOT_PASSWORD=secret \
--name mysql --publish 3306:3306 mysql:8-oracle
```

```shell
$ curl http://localhost:8080/tasks/1
{"id":1,"task":"Task DB 1","dueDate":"2022-12-03"}

$ curl -H 'Content-Type: application/json' -X POST -d '{"task":"Task CURL 4","dueDate":"2022-12-03"}' http://localhost:8080/tasks
```
