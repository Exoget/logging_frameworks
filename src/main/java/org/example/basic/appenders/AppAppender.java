package org.example.basic.appenders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppAppender {

    // another loger class
    static final Logger logger = LoggerFactory.getLogger(AppAppender.class);

    public static void sayHello() {

        logger.info(" Hello from AppAppender class ...");
        logger.trace(" This is a trace msg from AppAppender class ... !");
    }

}
