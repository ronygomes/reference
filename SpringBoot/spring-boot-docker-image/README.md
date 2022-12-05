## Spring Boot REST Introduction Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, Spring Boot DevTools


`spring-boot-maven-plugin` can generate docker images. Following configurations are added in
**pom.xml** file.

```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <image>
                    <name>ronygomes/reference-${project.artifactId}:${project.version}</name>
                </image>
                <pullPolicy>IF_NOT_PRESENT</pullPolicy>
            </configuration>
        </plugin>
    </plugins>
</build>
```

If docker daemon is running, executing following command will generate docker image named
`ronygomes/reference-spring-boot:0.0.1-SNAPSHOT`

```shell
$ ./mvnw spring-boot:build-image
```
It downloads JRE and image layers from internet, so good internet connect is required.

Run and test the image
```shell
$ docker container run -p 8080:8080 ronygomes/reference-spring-boot:0.0.1-SNAPSHOT
$ curl http://localhost:8080/greet
{"message":"Hello World!"}
```
