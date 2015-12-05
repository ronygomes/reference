package me.ronygomes.cookbook.log4j;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Log4jFileAppender {
	public static void main(String args[]) throws IOException{
		
		FileAppender log0 = new FileAppender(new SimpleLayout(), "log0.log");
		FileAppender log1 = new FileAppender(new SimpleLayout(), "log1.log");
		
		Logger parentLogger = Logger.getLogger("me.rgomes.cookbook.log4j");
		Logger childLogger = Logger.getLogger("me.rgomes.cookbook.log4j.SimpleAppender");
		parentLogger.addAppender(log0);
		childLogger.addAppender(log1);
		
		//Both message will be saved to both file
		//because child logger inherited 
		//log0 form parent
		//if we add 'childLogger.setAdditivity(false)' 
		// it will not inherit
		childLogger.info("INFO message");
		childLogger.debug("DEBUG message");
	}
}
