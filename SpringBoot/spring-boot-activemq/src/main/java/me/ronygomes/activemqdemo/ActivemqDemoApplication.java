package me.ronygomes.activemqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ActivemqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqDemoApplication.class, args);
	}

}
