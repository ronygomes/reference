package me.ronygomes.reference.activemq.auto_ack;

import me.ronygomes.reference.activemq.domain.Person;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.junit.jupiter.api.*;

public class AutoAckProducerAndConsumerTest {

    private static final String QUEUE_NAME = "queue/persons";
    private static final String BROKER_URL = "tcp://localhost:61616";
    private static final String EMBEDDED_BROKER_URL = "vm://localhost:61616?create=false";

    private static BrokerService broker;

    private AutoAckProducerAndConsumer autoAck;

    @BeforeAll
    static void setupAll() throws Exception {
        /* Following code will also create an embedded broker if not exists
         *      <code>var factor = new ActiveMQConnectionFactory("vm://localhost");</code>
         * create=true is default, but it will stop embedded server when all connections are closed.
         * Then testSendAndReceiveMessage() will fail because consumer will try to read from fresh new
         * broker as produces closed the connection after sending message. Setting up broker is the cleanest solution
         */
        broker = new BrokerService();
        broker.addConnector(BROKER_URL);
        broker.start();
    }

    @AfterAll
    static void destroyAll() throws Exception {
        broker.stop();
    }

    @BeforeEach
    void setupStep() {
        autoAck = new AutoAckProducerAndConsumer(new ActiveMQConnectionFactory(EMBEDDED_BROKER_URL));
    }

    @Test
    void testSendAndReceiveMessageWithAutoAcknowledgement() {
        Person personToSend = new Person("John Doe", 25);
        autoAck.sendMessage(QUEUE_NAME, personToSend);
        Person receivedPerson = autoAck.receiveMessage(QUEUE_NAME);

        Assertions.assertNotSame(personToSend, receivedPerson);
        Assertions.assertEquals(personToSend, receivedPerson);
    }
}
