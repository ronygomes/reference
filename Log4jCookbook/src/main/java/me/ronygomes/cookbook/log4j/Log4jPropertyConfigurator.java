package me.ronygomes.cookbook.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jPropertyConfigurator {
	private static Logger logger = Logger.getLogger(Log4jPropertyConfigurator.class);

	public static void main(String args[]){
		String configFileName = args[0];
		PropertyConfigurator.configure(configFileName);
		logger.info("Configured with simple.properties");
	}
}
