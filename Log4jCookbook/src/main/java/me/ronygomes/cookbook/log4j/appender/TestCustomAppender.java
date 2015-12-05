package me.ronygomes.cookbook.log4j.appender;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class TestCustomAppender {
	public static void main(String args[]) {
		CustomAppender appender = new CustomAppender('*');
		PatternLayout layout = new PatternLayout("%t - %m%n");
		appender.setLayout(layout);
		
		Logger logger = Logger.getLogger(Log4jSocketAppender.class);
		logger.addAppender(appender);

		logger.info("Message1 using Custom appender");
		logger.info("Message2 using Custom appender");
	}

}
