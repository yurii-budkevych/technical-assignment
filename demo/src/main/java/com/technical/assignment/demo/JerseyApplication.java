package com.technical.assignment.demo;

import com.technical.assignment.demo.service.HelloWorld;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/")
public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        register(HelloWorld.class);
    }
}