package me.ronygomes.activemqdemo;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsConsumer implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    @JmsListener(destination = "${active-mq.topic}")
    public void onMessage(Message message) {
        try {
            String value = message.getBody(String.class);
            System.out.println("Got from JSM: " + value);
            log.info("Got from JSM: " + value);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
