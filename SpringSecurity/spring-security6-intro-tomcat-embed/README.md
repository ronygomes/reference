## Spring Security 6 Intro (Embedded Tomcat)

This project demonstrates Spring Security setup without Spring Boot. Then it runs in embedded tomcat.

If Java 17 is installed, following command will make the web project accessible at `http://localhost:8080`:
```shell
# run in from Gradle application plugin
$ /gradlew clean run
```

### How it Works

1. After context startup servlet container loads Service Provider Interface `javax.servlet.ServletContainerInitializer` 
2. `org.springframework.web.SpringServletContainerInitializer` is defined as SPI and is loaded by servlet container
3. Spring then loads `org.springframework.web.WebApplicationInitializer` implementations i.e. `me.ronygomes.reference.config.AppInitializer`
4. AppInitializer is configured with custom code:
   1. Loads Spring Security Annotated Configuration - `me.ronygomes.referene.SecurityConfiguration`
   2. Registers Spring WebMvc root filter - `org.springframework.web.filter.DelegatingFilterProxy`. It is configured to forward to `me.ronygomes.reference.config.SpringSecurityInitializer`.
5. SpringSecurityInitializer extends `org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer` which loads `org.springframework.security.web.FilterChainProxy` which is the entrypoint filter for Spring Security.

### Reference
* https://stackoverflow.com/questions/28131102/how-servlet-container-finds-webapplicationinitializer-implementations