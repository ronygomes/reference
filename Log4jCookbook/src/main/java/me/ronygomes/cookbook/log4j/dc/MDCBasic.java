package me.ronygomes.cookbook.log4j.dc;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PatternLayout;

public class MDCBasic {
	// MDC Mapped Diagnostic Contexts
	// In a server client model used
	// to identify specific user
	public static void main(String args[]){
		Logger logger = Logger.getLogger(MDCBasic.class);
		PatternLayout layout = new PatternLayout("%c %X{first} %X{last} : %m%n");
		ConsoleAppender appender = new ConsoleAppender(layout);
		
		logger.addAppender(appender);
		
		MDC.put("first", "Rony");
		MDC.put("last", "Gomes");
		logger.info("Hello");
		
		MDC.put("first", "Ducs");
		MDC.put("last", "Java");
		logger.info("Rocks!");
		
		// [Output]
		// me.rgomes.cookbook.log4j.mdc.MDCBasic Rony Gomes : Hello
		// me.rgomes.cookbook.log4j.mdc.MDCBasic Ducs Java : Rocks!
		
		MDC.remove("first");
		MDC.remove("last");

	}
}
