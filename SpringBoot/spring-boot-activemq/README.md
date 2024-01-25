## Spring Boot - ActiveMQ Topic Reference




### Run Spring Boot Application
Assuming SDKMan is installed, run the following command to install Java 17 and start the application.
```shell
$  sdk env
$ ./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```

### Install ActiveMQ Locally
This project is configured to run standalone with embedded server configured with `org.apache.activemq.broker.BrokerService`.

For running with local installed server of
`apache-activemq-5.18.3` download from [ActiveMQ Site](https://activemq.apache.org/components/classic/download/).

Start the ActiveMQ broker using following command:
```shell
$ apache-activemq-5.18.3/bin/activemq start
```

**ActiveMQ Management Console**: http://localhost:8161/admin

**Default ActiveMQ Connections**: tcp://localhost:61616

Also remove `org.apache.activemq.broker.BrokerService` from `ActiveMQConfig`

### Test Application

* GET /api/complex/{int}/{int} will **Produce** Complex object in JMS
* Will **Produce** 'new Complex({RandomInt}, {RandomInt}') message every 3s
* Consumer will log message as INFO  

### Profile

* `json-message` profile will utilize **JmsConsumer.java** which will send JSON Text Message in JSM
* `java-message` profile will utilize **JmsConsumerSimple.java** which will send Serialized Java Object in JMS

### Reference Documentation
For further reference, please consider the following sections:

* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#web)
* [Spring for Apache ActiveMQ 5](https://docs.spring.io/spring-boot/docs/3.2.0/reference/htmlsingle/index.html#messaging.jms.activemq)
* [Java Message Service API via Apache ActiveMQ Classic](https://spring.io/guides/gs/messaging-jms/)
