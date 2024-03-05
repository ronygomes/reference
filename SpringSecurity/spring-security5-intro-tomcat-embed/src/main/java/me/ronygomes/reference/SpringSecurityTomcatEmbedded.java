package me.ronygomes.reference;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class SpringSecurityTomcatEmbedded {

    private static final int TOMCAT_PORT = 8080;

    private static final String CONTEXT_ROOT = "/";
    private static final String TOMCAT_WEBAPP_DIRECTORY = new File("src/main/webapp/").getAbsolutePath();
    private static final String ADDITION_WEB_INF_CLASSES = new File("build/classes").getAbsolutePath();
    private static final String WEB_INF_CLASSES = "/WEB-INF/classes";


    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(TOMCAT_PORT);

        Context context = tomcat.addWebapp(CONTEXT_ROOT, TOMCAT_WEBAPP_DIRECTORY);

        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, WEB_INF_CLASSES, ADDITION_WEB_INF_CLASSES, CONTEXT_ROOT));

        context.setResources(resources);

        tomcat.start();
        tomcat.getConnector();
        tomcat.getServer().await();
    }
}
