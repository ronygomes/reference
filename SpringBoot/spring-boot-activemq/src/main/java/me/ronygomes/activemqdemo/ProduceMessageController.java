package me.ronygomes.activemqdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduceMessageController {

    @Autowired
    JmsProducer jmsProducer;

    @GetMapping(value="/api/complex/{a}/{b}")
    public String sendMessage(@PathVariable int a, @PathVariable int b){
        jmsProducer.sendMessage(a, b);
        return String.format("JMS Sent: %d, %d", a, b);
    }
}
