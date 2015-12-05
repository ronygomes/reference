package me.ronygomes.cookbook.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;

public class Log4jThreshold {
	private static Logger logger = Logger.getLogger(Log4jThreshold.class);
	
	public static void main(String args[]){
		BasicConfigurator.configure();
		
		// Specify a system level Threshold
		// All log message under this Threshold will not be printed
		LoggerRepository repository = logger.getLoggerRepository();
		repository.setThreshold(Level.INFO);
		
		logger.debug("DEBUG message");
		logger.info("INFO message");
		logger.warn("WARN message");
		logger.error("ERROR message");
		logger.fatal("FATAL message");
		
		repository.setThreshold(Level.OFF);
		
		logger.debug("DEBUG message 2");
		logger.info("INFO message 2");
		logger.warn("WARN message 2");
		logger.error("ERROR message 2");
		logger.fatal("FATAL message 2");
		
		/*
		 * [Output]
		 * 0 [main] INFO me.rgomes.cookbook.log4j.Log4jThreshold  - INFO message
		 * 1 [main] WARN me.rgomes.cookbook.log4j.Log4jThreshold  - WARN message
         * 1 [main] ERROR me.rgomes.cookbook.log4j.Log4jThreshold  - ERROR message
         * 1 [main] FATAL me.rgomes.cookbook.log4j.Log4jThreshold  - FATAL message 
		 */
		
	}
}
