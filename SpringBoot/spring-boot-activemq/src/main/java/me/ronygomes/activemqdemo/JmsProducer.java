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

    @Value("${active-mq.topic}")
    private String topic;

    private JmsTemplate jmsTemplate;

    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend(topic, message);
    }

    @Scheduled(fixedDelay = 1000)
    public void produceMessage() {
        sendMessage("Random: " + new Random().nextInt());
        log.info("Random: " + new Random().nextInt());
    }
}
