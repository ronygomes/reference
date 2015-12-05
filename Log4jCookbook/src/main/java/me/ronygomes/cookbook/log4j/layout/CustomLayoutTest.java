package me.ronygomes.cookbook.log4j.layout;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;

public class CustomLayoutTest {
	public static void main(String args[]){
		Logger logger = Logger.getLogger(CustomLayoutTest.class);
		
		ConsoleAppender appender = new ConsoleAppender(new CustomLayout());
		logger.addAppender(appender);
		
		logger.info("Used with custom layout");
	}
}
