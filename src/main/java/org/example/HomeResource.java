package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    // Logger SLF4j facade
    Logger logger = LoggerFactory.getLogger(HomeResource.class);

    @RequestMapping("/")
    public String home(){

        // par défaut, c'est le framework logback qui est utilisé, configuré avec le fichier application.properties
        logger.error("Error happened ! ");
        logger.trace("TRACE happened ! ");
        return "Hello from controller !";
    }
}
