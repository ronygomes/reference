package me.ronygomes.activemqdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduceMessageController {

    @Autowired
    JmsProducer jmsProducer;

    @GetMapping(value="/api/{message}")
    public String sendMessage(@PathVariable String message){
        jmsProducer.sendMessage(message);
        return "@JMS: " + message;
    }
}
