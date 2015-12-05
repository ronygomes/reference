package me.ronygomes.cookbook.log4j.filter;

public class BasicFilterInfo {
	/*
	  	package org.apache.log4j.spi;
		
		public abstract class Filter implements OptionHandler {
			public Filter next;
			public static final int DENY = -1; // current is dropped + all are skipped (Failed)
			public static final int NEUTRAL = 0; // process next logEvent (Continue)
			public static final int ACCEPT = 1; // execute current logLevel (Execute this and ignore remaining)
			public void activateOptions() {}
			abstract public int decide(LoggingEvent event);
		}
	 */
	
	/*
	    // Can be added to appender to reject message
	 	<filter class="org.apache.log4j.varia.StringMatchFilter">
			<param name="StringToMatch" value="Spring" />
			<param name="AcceptOnMatch" value="false" />
		</filter>
	*/

}
