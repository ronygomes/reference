package me.ronygomes.reference.activemq.auto_ack;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.ExceptionListener;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import me.ronygomes.reference.activemq.domain.Person;
import me.ronygomes.reference.activemq.util.TextSerializableHelper;

import java.util.ArrayList;
import java.util.List;

import static me.ronygomes.reference.activemq.util.MessagingHelper.*;

public class AutoAckProducerAndConsumer {

    private static final ExceptionListener DEFAULT_CONSUMER_EXCEPTION_LISTENER = ex ->
            System.err.println("JMS Exception: " + ex.getMessage());

    private final ConnectionFactory connectionFactory;

    public AutoAckProducerAndConsumer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void sendMessage(String destinationName, Person person) {

        withConnection(connectionFactory, null, connection ->
                withQueueProducer(connection, destinationName, (producer, session) -> {
                    try {
                        TextMessage message = session.createTextMessage(TextSerializableHelper.serialize(person));
                        producer.send(message);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                })
        );
    }

    public Person receiveMessage(String destinationName) {
        List<Person> receivedPersons = new ArrayList<>();

        withConnection(connectionFactory, DEFAULT_CONSUMER_EXCEPTION_LISTENER, connection ->
                withQueueConsumer(connection, destinationName, consumer ->
                        readText(consumer).ifPresent(text ->
                                receivedPersons.add(TextSerializableHelper.deserialize(text))
                        )
                )
        );

        return !receivedPersons.isEmpty() ? receivedPersons.get(0) : null;
    }
}
