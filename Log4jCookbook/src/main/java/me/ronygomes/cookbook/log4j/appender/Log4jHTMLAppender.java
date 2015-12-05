package me.ronygomes.cookbook.log4j.appender;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.HTMLLayout;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class Log4jHTMLAppender {
	public static void main(String args[]) throws IOException{
		
		HTMLLayout layout = new HTMLLayout();
		layout.setLocationInfo(true);
		
		FileAppender logFile = new FileAppender(layout, "Log.html", false);
		
		Logger logger = Logger.getLogger(Log4jHTMLAppender.class);
		logger.addAppender(logFile);
		
		logger.info("Message 1");
		logger.debug("Message 2");
		logger.warn("Message 3");
		
	}
}
