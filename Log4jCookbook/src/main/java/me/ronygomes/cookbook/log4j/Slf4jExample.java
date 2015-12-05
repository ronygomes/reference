package me.ronygomes.cookbook.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jExample {
	public static void main(String args[]){
		Logger logger = LoggerFactory.getLogger(Slf4jExample.class);
		
		logger.info("This {} is really {}", "feature", "cool");
	}
}
