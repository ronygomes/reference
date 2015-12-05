package me.ronygomes.cookbook.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggerLevel {
	private static Logger baseLogger = Logger.getLogger("me.rgomes.cookbook.log4j");
	private static Logger childLogger = Logger.getLogger("me.rgomes.cookbook.log4j.LoggerLevel");
	
	public static void main(String args[]){
		BasicConfigurator.configure();
		/*
		 * If baseLogger is assigned
	     * a level all child has same level
	     * so childLogger has INFO level
		 */
		baseLogger.setLevel(Level.INFO);
		
		// As, ALL < DEBUG < INFO < WARN < ERROR < FATAL < OFF
		// so debug() will not printed
		baseLogger.info("simple message");
		baseLogger.debug("simple message");
		childLogger.info("simple message");
		childLogger.debug("simple message");
		
		/* [Output]
		 * 0 [main] INFO me.rgomes.cookbook.log4j  - simple message
		 * 1 [main] INFO me.rgomes.cookbook.log4j.LoggerLevel  - simple message
         */		
	}
}
