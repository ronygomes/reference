package me.ronygomes.cookbook.log4j.appender;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;

//Simple Appender which will print a horizontal line
public class CustomAppender extends AppenderSkeleton {
	private char printChar;

	public CustomAppender() {
		printChar = '-';
	}

	public CustomAppender(char printChar) {
		this.printChar = printChar;
	}

	@Override
	protected void append(LoggingEvent event) {
		if (layout == null) {
			errorHandler.error("No layout found for appender: " + name, null,
					ErrorCode.MISSING_LAYOUT);
			return;
		}
		
		System.out.print(this.layout.format(event));
		int length = layout.format(event).length();
		
		for (int i = 0; i < length; i++) {
			System.out.print(printChar);
		}
		System.out.println();

		if (layout.ignoresThrowable()) {
			String errors[] = event.getThrowableStrRep();
			if (errors != null) {
				for (int i = 0; i < errors.length; i++) {
					System.out.println(errors[i]);
				}
			}
		}
	}

	@Override
	public void close() {
		if (!closed) {
			closed = true;
		}
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

	public char getPrintChar() {
		return printChar;
	}

	public void setPrintChar(char printChar) {
		this.printChar = printChar;
	}

}
