## Spring Security Thymeleaf Reference

This project configures following things:

* Thymeleaf Private/Public page with usage of `sec:authorize` attribute
* Spring Security config for UserDetailsService with user and admin user
* Spring Security config for custom login, logout, remember-me,
* formLogin with naive AuthenticationFailureHandler implementation
* Default csrf and httpBasic configuration
* Spring Security config for session management with limiting maximumSessions

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Spring Security, Spring Boot DevTools

Assuming JDK 17 is installed, run the project with following command:

```shell
./mvnw spring-boot:run
```


