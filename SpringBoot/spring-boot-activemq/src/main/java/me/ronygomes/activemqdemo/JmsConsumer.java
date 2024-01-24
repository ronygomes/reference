package me.ronygomes.activemqdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Profile(ActiveMQConfig.PROFILE_JSON_MESSAGE)
public class JmsConsumer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // Here Conversion is done by Sprint with custom ActiveMQConfig#converter()
    @JmsListener(destination = "${active-mq.queue}")
    public void onMessage(Complex message) {
        System.out.println("JmsConsumer: " + message);
        log.info("JmsConsumer: " + message);
    }

}
