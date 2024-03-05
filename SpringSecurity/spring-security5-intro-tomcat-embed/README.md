## Spring Security 5 Intro (Embedded Tomcat)

This project demonstrates Spring Security setup without Spring Boot. Then it runs in embedded tomcat.

If SDKMan is installed, following commands will make the web project accessible at `http://localhost:8080/index`:
```shell
# Following command will install Java 11.0.22-amzn
$ sdk env install

# run in from Gradle application plugin
$ ./gradlew clean build run
```

### How it Works

1. After context startup servlet container loads Service Provider Interface `javax.servlet.ServletContainerInitializer` 
2. `org.springframework.web.SpringServletContainerInitializer` is defined as SPI and is loaded by servlet container
3. Spring then loads `org.springframework.web.WebApplicationInitializer` implementations i.e. `me.ronygomes.reference.config.AppInitializer`
4. AppInitializer is configured with custom code which loads Spring MVC configuration from `me.ronygomes.reference.config.RootConfiguration`
5. RootConfiguration scans for Spring Security Configuration at `me.ronygomes.reference.config.SecurityConfiguration`
6. SpringSecurityInitializer extends `org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer` which loads `org.springframework.security.web.FilterChainProxy` which is the entrypoint filter for Spring Security.
7. Both annotation `@EnableMvc` and `@EnableWebSecurity` is used, so no other custom configuraiton is required

### Reference
* https://stackoverflow.com/questions/28131102/how-servlet-container-finds-webapplicationinitializer-implementations