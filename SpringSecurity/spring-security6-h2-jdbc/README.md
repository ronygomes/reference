## Spring Security H2 JDBC Reference

This project configures an Embedded H2 database and uses `org.springframework.security.provisioning.JdbcUserDetailsManager`
for configuring UserDetailsService.

This project was created using https://start.spring.io/ with following parameter:

* Project: Gradle
* Language: Java
* Spring Boot: 3.2.3
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Spring Security, Spring Data JDBC, H2 Database

Assuming JDK 17 is installed, run the project with following command:

```shell
./mvnw clean build spring-boot:run
```

