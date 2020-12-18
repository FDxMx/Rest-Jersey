package it.rest.LibreriaRest.configuration;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import it.rest.LibreriaRest.controller.LibreriaController;

@Configuration
public class LibreriaConfiguration extends ResourceConfig {
	
	private final Logger logger = LoggerFactory.getLogger(LibreriaConfiguration.class);
	
	@PostConstruct
    public void registerController() {
        logger.info("Registration controller....");
        register(LibreriaController.class);
    }

}
