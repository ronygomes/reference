## Spring Security Introduction Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Spring Security, Spring Boot DevTools

Assuming JDK 17 is installed, run the project with following commnd:

```shell
./mvnw spring-boot:run
```

Spring Security works with 'SecurityFilterChain', which is list of filters. One way of updating the configuration is to define new 
`SecurityFilterChain` bean.

By default a credential with name 'user' and random password is generated. Folllowing configuration will create a static user/password

```
spring.security.user.name=admin
spring.security.user.password=12345
```


```shell
$ curl -u 'admin:12345' http://localhost:8080/tasks/1
{"id":1,"task":"Task 1","dueDate":"2022-12-04"}
```

## Class Details
[Application.java][1] - `main()` class for this Spring Boot Application

[TaskResource.java][2] - Restful Task Create/Read/Delete. It is convention to use 'Resource' as suffix

[SpringSecurityConfig.java][3] - Custom `SecurityFilterChain` bean configuration    

[1]: https://github.com/ronygomes/reference/blob/master/SpringSecurity/spring-security-intro/src/main/java/me/ronygomes/reference/springboot/Application.java
[2]: https://github.com/ronygomes/reference/blob/master/SpringSecurity/spring-security-intro/src/main/java/me/ronygomes/reference/springboot/TaskResource.java
[3]: https://github.com/ronygomes/reference/blob/master/SpringSecurity/spring-security-intro/src/main/java/me/ronygomes/reference/springboot/SpringSecurityConfig.java