package me.ronygomes.reference.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Note to need to specify URL as will fetch that data from Eureka Server
@FeignClient(name="greet-service")
public interface GreetProxy {
    
    @GetMapping("/greet/{name}")
	public Message generateGreet(@PathVariable String name);
}
