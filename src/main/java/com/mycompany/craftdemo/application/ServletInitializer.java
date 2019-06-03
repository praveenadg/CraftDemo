package com.mycompany.craftdemo.application;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * SpringBootServletInitializer class binds Servlet, Filter and ServletContextInitializer beans from the application context to the server.
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * uses SpringApplicationBuilder to simply register our class as a configuration class of the application
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CraftDemoApplication.class);
    }

}
