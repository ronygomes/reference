## Spring Boot - ActiveMQ Topic Reference


### Install ActiveMQ
This project was tested with local installation of `apache-activemq-5.18.3` downloaded from 
[ActiveMQ Site](https://activemq.apache.org/components/classic/download/).

Start the ActiveMQ broker using following command:
```shell
$ apache-activemq-5.18.3/bin/activemq start
```

**ActiveMQ Management Console**: http://localhost:8161/admin

**Default ActiveMQ Connections**: tcp://localhost:61616

### Run Spring Boot Application
Assuming SDKMan is installed, run the following command to install Java 17 and start the application.
```shell
$  sdk env
$ ./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

### Test Application

* GET /api/<Message> will produce <Message> in JMS
* Will Produce 'Random: <RandomInt>' message every 1s
* Consumer will log message as INFO  


### Reference Documentation
For further reference, please consider the following sections:

* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#web)
* [Spring for Apache ActiveMQ 5](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#messaging.jms.activemq)
* [Java Message Service API via Apache ActiveMQ Classic](https://spring.io/guides/gs/messaging-jms/)