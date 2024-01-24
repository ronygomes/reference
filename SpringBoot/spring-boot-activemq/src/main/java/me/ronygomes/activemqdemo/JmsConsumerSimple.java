package me.ronygomes.activemqdemo;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

// It is possible to Exchange basic types with MessageListener#onMessage(Message)
@Component
@Profile(ActiveMQConfig.PROFILE_JAVA_MESSAGE)
public class JmsConsumerSimple implements MessageListener {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    // Note: If @JmsListener omitted will not work
    @JmsListener(destination = "${active-mq.queue}")
    public void onMessage(Message message) {
        try {
            // Able to serialize as Complex implements java.io.Serializable 
            Complex value = message.getBody(Complex.class);
            System.out.println("JmsConsumerSimple: " + value);
            log.info("JmsConsumerSimple: " + value);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
