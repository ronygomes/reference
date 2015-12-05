package me.ronygomes.cookbook.log4j;

import java.io.IOException;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log4jPatternLayout {
	public static void main(String args[]) throws IOException{
		Logger logger = Logger.getLogger(Log4jPatternLayout.class);
		
		PatternLayout layout = new PatternLayout("%r [%t] %-5p %c - %m%n");
		ConsoleAppender appender = new ConsoleAppender(layout);
		logger.addAppender(appender);
		logger.info("Message");
		logger.info("Message");
		
		// [Output]
		// 0 [main] INFO  me.rgomes.cookbook.log4j.Log4jPatternLayout - Message
		// %r [%t] %-5p %c - %m%n
		// %r - time elapsed since start of program
		// %t - thread executing
		// %-5p - Level
		// %c - Logger name
		// %m - Message
		// %n - new line
		
	}
}
