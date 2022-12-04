## Spring Cloud Config Server Reference

In microservice architecture it is better to store configuration in a central place. Then all microservices can read from it.
Spring Cloud makes it very easy to configure it.

This project contain 3 parts
* **global-config:** All global config are stored in this directory 
* **config-server:** It exposes configuration from **global-config** directory
* **greet-service:** Reads from **config-server**

### Run

`global-config` folder is expected to be in `/tmp/global-config`. So move this directory and do a commit

```
$ mv -r global-config /tmp
$ cd /tmp/global-config
$ git init && git add . && git commit -m "Initial commit"
```

Assuming JDK 17 is installed, run the project with following commnd:

```shell
# Run config server
$ cd config-server/
$ ./mvnw spring-boot:run

# Run greet-service
$ cd greet-service/
$ ./mvnw spring-boot:run
```

Run the project using curl:
```shell
# If config server is up
$ curl http://localhost:8080/greet
{"message":"Greeting from Global Configuration"}

# If config server is down
$ curl http://localhost:8080/greet
{"message":"Welcome from greet-service"}
```