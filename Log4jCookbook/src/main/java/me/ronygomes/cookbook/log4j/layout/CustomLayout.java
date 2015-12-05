package me.ronygomes.cookbook.log4j.layout;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

public class CustomLayout extends Layout{

	@Override
	public void activateOptions() {
	}

	@Override
	public String format(LoggingEvent event) {
		String level = event.getLevel().toString();
		String className = event.getLocationInformation().getClassName();
		String lineNumber = event.getLocationInformation().getLineNumber();
		String messgage = event.getMessage().toString();
		
		return String.format("[%s] - %s:%s - %s\n", 
				level, className, lineNumber, messgage);
	}

	@Override
	public boolean ignoresThrowable() {
		return true;
	}

}
