package me.ronygomes.reference.springcloud;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
    
    @Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/greet/**")
						.uri("lb://greet-service"))
				.build();

        // TODO: Write a route for following url Rewrite
        // /greet-old?name=Java -> greet/Java
	}
}
