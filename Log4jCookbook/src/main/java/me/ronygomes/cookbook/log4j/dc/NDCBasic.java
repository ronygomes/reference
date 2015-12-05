package me.ronygomes.cookbook.log4j.dc;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.apache.log4j.PatternLayout;

public class NDCBasic {
	// NDC Nested Diagnostic Contexts
	public static void main(String args[]){
		Logger logger = Logger.getLogger(NDCBasic.class);
		PatternLayout layout = new PatternLayout("%x : %m%n");
		ConsoleAppender appender = new ConsoleAppender(layout);
		
		logger.addAppender(appender);
		NDC.push("C1");
		NDC.push("C2");
		logger.info("Hello");
		NDC.pop();
		logger.info("Rocks!");
		NDC.remove();
		
		// [Output]
		// C1 C2 : Hello
		// C1 : Rocks!

		

	}
}
