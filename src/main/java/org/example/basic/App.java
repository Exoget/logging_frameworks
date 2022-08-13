package org.example.basic;

import org.example.basic.appenders.AppAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    /**
     * All loggers are descendants of the predefined root logger, the App logger will inherit the configuration.
     * By default il wil takes the root level="debug" from logback.xml
     */
    static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info(" This is an info msg ... !");
        logger.debug(" This is an debug msg ... !");
        // ne sera pas affiché car le root log est configuré avec le niveau debug
        logger.trace(" This is a trace msg ... !");
        AppAppender.sayHello();
    }

}
