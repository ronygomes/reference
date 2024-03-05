package me.ronygomes.reference.servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// This servlet is used for testing embedded tomcat server configuration
@WebServlet(name = "HelloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException {

        ServletOutputStream out = resp.getOutputStream();
        out.write("Hello World!".getBytes());

        out.flush();
        out.close();
    }
}