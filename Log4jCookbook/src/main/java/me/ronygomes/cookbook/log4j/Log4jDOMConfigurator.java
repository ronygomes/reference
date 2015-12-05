package me.ronygomes.cookbook.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jDOMConfigurator {
	private static Logger logger = Logger.getLogger(Log4jDOMConfigurator.class);

	public static void main(String args[]){
		String configFileName = args[0];
		DOMConfigurator.configure(configFileName);
		logger.info("Configured with simple-config.xml");
	}
}
