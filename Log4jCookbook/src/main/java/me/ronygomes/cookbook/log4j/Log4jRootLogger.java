package me.ronygomes.cookbook.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log4jRootLogger {
	private static Logger rootLogger = Logger.getRootLogger();	
	
	/*
	 * Logger.getRootLogger() returns the root logger
	 * if two logger 'me.rgomes.cookbook' and 'me.rgomes.cookbook.ClassName'
	 * then 'me.rgomes.cookbook' is parent of 'me.rgomes.cookbook.ClassName'
	 * rootLogger is root of all logger
	 */
	public static void main(String args[]){
		/*
		 * [Output]
		 * 0 [main] INFO root  - Hello World!
		 */
		BasicConfigurator.configure();
		rootLogger.info("Hello World!");
	}
}
