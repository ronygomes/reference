package me.ronygomes.activemqdemo;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class ActiveMQConfig {

    public static final String PROFILE_JSON_MESSAGE = "json-message";
    public static final String PROFILE_JAVA_MESSAGE = "java-message";

    // 
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Bean
    public ConnectionFactory activeMQConnectionFactory(
            @Value("${active-mq.broker-url}") String brokerUrl) {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        

        if (PROFILE_JAVA_MESSAGE.equals(activeProfile)) {
            // Required only for JmsConsumerSimple, as it serialized using java.io.Serializable
            activeMQConnectionFactory.setTrustAllPackages(true);
        }

        return activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory activeMQConnectionFactory,
                                   @Value("${active-mq.queue}") String queueName) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setDefaultDestinationName(queueName);

        if (PROFILE_JSON_MESSAGE.equals(activeProfile)) {
            jmsTemplate.setMessageConverter(converter());
        }

        return jmsTemplate;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory cf = new DefaultJmsListenerContainerFactory();
        cf.setConnectionFactory(activeMQConnectionFactory);
        cf.setPubSubDomain(true);

        if (PROFILE_JSON_MESSAGE.equals(activeProfile)) {
            cf.setMessageConverter(converter());
        }

        return cf;
    }

    /* If you don't provide broker service it will search ActiveMQ running server in localhost 
     * This line will create an embedded ActiveMQ server.
    */
    @Bean
    public BrokerService broker() throws Exception {
        BrokerService service = new BrokerService();
        service.addConnector("tcp://0.0.0.0:61616");
        service.setPersistent(false);
        return service;
    }

    private MappingJackson2MessageConverter converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        // Will serialize JMSMessage as JSON text
        converter.setTargetType(MessageType.TEXT);

        // Will keep Class type in _type JMSMessage header
        // Will be required for deserialization
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
