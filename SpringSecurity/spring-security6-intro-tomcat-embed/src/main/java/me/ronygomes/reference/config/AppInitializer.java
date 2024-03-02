package me.ronygomes.reference.config;

import jakarta.servlet.ServletContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import static org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;

/* This class is loaded by org.springframework.web.SpringServletContainerInitializer
 * which is a java.util.ServiceLoader of type javax.servlet.ServletContainerInitializer
 *
 * Servlet container invokes the SPI when context is loaded
 */
public class AppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext sc) {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(SecurityConfiguration.class);
        sc.addListener(new ContextLoaderListener(root));

        /*
         * DelegatingFilterProxy is a filter of spring-mvc, which delegates to 'springSecurityFilterChain' (DEFAULT_FILTER_NAME)
         *
         * SpringSecurityInitializer (via AbstractSecurityWebApplicationInitializer) initializes Spring Security main
         * filter org.springframework.security.web.FilterChainProxy
         */
        sc.addFilter("securityFilter", new DelegatingFilterProxy(DEFAULT_FILTER_NAME))
                .addMappingForUrlPatterns(null, false, "/*");
    }
}
