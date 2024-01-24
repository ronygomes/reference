package me.ronygomes.activemqdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class JmsProducer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${active-mq.queue}")
    private String destination;

    private JmsTemplate jmsTemplate;

    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(int a, int b) {
        // Can send to any destination
        jmsTemplate.convertAndSend(destination, new Complex(a, b));

        // Sends to default destination, here ${active-mq.queue}
        jmsTemplate.convertAndSend(new Complex(0, 0));
    }

    @Scheduled(fixedDelay = 3000)
    public void produceMessage() {
        Random r = new Random();
        sendMessage(r.nextInt(), r.nextInt());
        log.info("Complex Sent");
    }
}
