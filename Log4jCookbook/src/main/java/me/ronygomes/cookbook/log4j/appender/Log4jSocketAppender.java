package me.ronygomes.cookbook.log4j.appender;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.net.SocketAppender;

public class Log4jSocketAppender {
	public static void main(String args[]) throws IOException {
		// Linux command for listening localhost:8936
		// nc -l localhost 8936 # But it's unable to read Java Socket Message
		// java -cp log4j-1.2.5.jar org.apache.log4j.net.SimpleSocketServer 8936 socket-conf.properties
		// is a simple log4j server
		SocketAppender appender = new SocketAppender("localhost", 4454);
		PatternLayout layout = new PatternLayout("%m%n");
		appender.setLayout(layout);

		Logger logger = Logger.getLogger(Log4jSocketAppender.class);
		logger.addAppender(appender);

		logger.info("Message send on socket");

	}
}
