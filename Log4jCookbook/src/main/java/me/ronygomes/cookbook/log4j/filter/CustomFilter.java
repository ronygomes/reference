package me.ronygomes.cookbook.log4j.filter;

import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

//Simple filter which will 
public class CustomFilter extends Filter{
	
	// DENY = -1; 
	// NEUTRAL = 0; 
	// ACCEPT = 1;
	
	@Override
	public int decide(LoggingEvent event) {
		String message = event.getRenderedMessage();
		
		if(message.lastIndexOf("fuck") > -1){
			return DENY;
		}
		return NEUTRAL;
	}

}
