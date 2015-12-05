package me.ronygomes.cookbook.log4j.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

/*  Best choice for tomcat 7
 	<servlet>
		<servlet-name>log4j-loader</servlet-name>
		<servlet-class> me.rgomes.cookbook.log4j.web.Log4jServlet</servlet-class>
		<init-param>
			<param-name>log4j-config-file</param-name>
			<param-value>WEB-INF/classes/log4j.properties</param-value>
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
 */

/*
	EJB forbids AsyncAppender(own thread), FileAppender
	Use ConsoleAppender, JMSAppernder, SocketAppender
 
 */
public class Log4jServlet extends HttpServlet {
	public void init() {
		String prefix = getServletContext().getRealPath("/");
		String fileName = getInitParameter("log4j-config-file");
		if (fileName != null) {
			PropertyConfigurator.configure(prefix + fileName);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
	}
}
