package me.ronygomes.cookbook.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log4jLoggerMethods {
	private static Logger logger = Logger.getLogger(Log4jLoggerMethods.class);
	
	public static void main(String args[]){
		BasicConfigurator.configure();
		
		Throwable throwable = new Exception();
		
		//Only log message
		logger.debug("DEBUG message");
		logger.info("INFO message");
		logger.warn("WARN message");
		logger.error("ERROR message");
		logger.fatal("FATAL message");
		
		//Log message with cause
		logger.debug("DEBUG message", throwable);
		logger.info("INFO message", throwable);
		logger.warn("WARN message", throwable);
		logger.error("ERROR message", throwable);
		logger.fatal("FATAL message", throwable);
		
		//ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF
		logger.log(Level.WARN, "WARN message");
	}
}
