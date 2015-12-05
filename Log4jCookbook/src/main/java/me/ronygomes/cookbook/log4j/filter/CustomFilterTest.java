package me.ronygomes.cookbook.log4j.filter;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class CustomFilterTest {
	public static void main(String args[]){
		ConsoleAppender appender = new ConsoleAppender(new SimpleLayout());
		appender.addFilter(new CustomFilter());
		
		Logger logger = Logger.getLogger(CustomFilterTest.class);
		logger.addAppender(appender);
		
		logger.info("This message contain fuck.");
		logger.info("This message will be printed");
	}
}
