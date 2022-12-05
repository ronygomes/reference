package me.ronygomes.reference.springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="greet-service", url="localhost:8080")
public interface GreetProxy {
    
    @GetMapping("/greet/{name}")
	public Message generateGreet(@PathVariable String name);
}
