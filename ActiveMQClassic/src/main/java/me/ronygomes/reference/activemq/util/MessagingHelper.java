package me.ronygomes.reference.activemq.util;

import jakarta.jms.*;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MessagingHelper {

    private static final int CONSUMER_WAIT_DURATION_MS = 1000;

    public static void withConnection(ConnectionFactory factory,
                                      ExceptionListener exceptionListener,
                                      Consumer<Connection> callback) {

        try (var connection = factory.createConnection()) {
            if (Objects.nonNull(exceptionListener)) {
                connection.setExceptionListener(exceptionListener);
            }

            connection.start();
            callback.accept(connection);
        } catch (JMSException exception) {
            exception.printStackTrace();
        }
    }

    public static void withQueueConsumer(Connection connection, String queueName, Consumer<MessageConsumer> callback) {
        withSession(connection, session -> {
            withQueue(session, queueName, destination -> {
                try {
                    MessageConsumer consumer = session.createConsumer(destination);
                    callback.accept(consumer);
                    consumer.close();

                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            });
        });
    }

    public static void withQueueProducer(Connection connection, String queueName, BiConsumer<MessageProducer, Session> callback) {
        withSession(connection, session -> {
            withQueue(session, queueName, destination -> {
                try {
                    MessageProducer producer = session.createProducer(destination);
                    producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                    callback.accept(producer, session);
                    producer.close();

                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            });
        });
    }

    public static void withQueue(Session session, String queueName, Consumer<Destination> callback) {
        try {
            Destination destination = session.createQueue(queueName);
            callback.accept(destination);

        } catch (JMSException exception) {
            exception.printStackTrace();
        }
    }

    public static void withSession(Connection connection, Consumer<Session> callback) {
        try (var session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
            callback.accept(session);
        } catch (JMSException exception) {
            exception.printStackTrace();
        }
    }

    public static Optional<String> readText(MessageConsumer consumer) {
        try {

            Message message = consumer.receive(CONSUMER_WAIT_DURATION_MS);
            if (message instanceof TextMessage textMessage) {
                return Optional.of(textMessage.getText());
            }
        } catch (JMSException exception) {
            exception.printStackTrace();
        }

        return Optional.empty();
    }
}
