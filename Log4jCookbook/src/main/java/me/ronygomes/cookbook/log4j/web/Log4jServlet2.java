package me.ronygomes.cookbook.log4j.web;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Hierarchy;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.RootCategory;

/*
 	In other servlet, use
	Hierarchy hierarchy = (Hierarchy) context.getAttribute("hierarchy");
	hierarchy.getLogger(ClassName.class);
*/

public class Log4jServlet2 extends HttpServlet {
	public void init() {
		ServletContext context = getServletContext();
		String prefix = context.getRealPath("/");
		Hierarchy hierarchy = new Hierarchy(new RootCategory(Level.DEBUG));
		context.setAttribute("hierarchy", hierarchy);
		
		String fileName = getInitParameter("log4j-config-file");
		if (fileName != null) {
			new PropertyConfigurator().doConfigure(prefix + fileName, hierarchy);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
	}
}
