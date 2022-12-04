## Spring Boot Config Server Reference

This project was created using https://start.spring.io/ with following parameter:

* Project: Maven
* Language: Java
* Spring Boot: 3.0.0
* Packaging: Jar
* Java: 17 (Required for v3)
* Dependencies: Spring Web, **Config Server**, Spring Boot DevTools


Default configuration can be found in following url:
```
http://localhost:8888/greet-service/default
```

Following configuration is added for this project

```
server.port=8888

# Defautl git branch is master
spring.cloud.config.server.git.default-label=master
spring.cloud.config.server.git.uri=file:///tmp/global-config
```

Assuming JDK 17 is installed, run the project with following command:

```shell
./mvnw spring-boot:run
```

```shell
$ curl http://localhost:8888/greet-service/default

{
   "name":"greet-service",
   "profiles":[
      "default"
   ],
   "label":null,
   "version":"62dcfea462f9daf3495db75cc07516089adfd8fd",
   "state":null,
   "propertySources":[
      {
         "name":"file:///tmp/global-config/greet-service.properties",
         "source":{
            "greet-service.welcome-message":"Greeting from Global Configuration"
         }
      }
   ]
}
```
