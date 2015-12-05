package me.ronygomes.cookbook.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log4jHelloWorld {
	private static Logger logger = Logger.getLogger("me.rgomes.cookbook.log4j.Log4jHelloWorld");
	
	/*
	 * OR
	 * private static Logger logger = Logger.getLogger(Log4jHelloWorld.class.getName());
	 * private static Logger logger = Logger.getLogger(Log4jHelloWorld.class);
	 * Calling Logger with same name will return same instance
	 */
	
	public static void main(String args[]){
		/*
		 *	if we don't specify BasicConfigurator.configure() a warning will be displayed
		 *	[Output]
		 *	log4j:WARN No appenders could be found for logger (me.rgomes.cookbook.log4j.Log4jHelloWorld).
		 *	log4j:WARN Please initialize the log4j system properly.
		 */
		BasicConfigurator.configure(); // default to DEBUG
		logger.info("Hello World!");
	}
}
