package me.ronygomes.reference.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LogGlobalFilter implements GlobalFilter {

	private Logger logger = LoggerFactory.getLogger(LogGlobalFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange e, GatewayFilterChain chain) {
		logger.info("API Gateway Log -> {}", e.getRequest().getPath());
		
				return chain.filter(e);
	}

}