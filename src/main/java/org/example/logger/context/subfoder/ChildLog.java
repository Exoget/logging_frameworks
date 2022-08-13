package org.example.logger.context.subfoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChildLog {

    /**
     *  All loggers are descendants of the predefined root logger, the App logger will inherit the configuration.
     *  In this exemple the root parent is parentLogger from package org.example.logger.context.
     */
    static final Logger childlogger = LoggerFactory.getLogger("org.example.logger.context.subfoder");

    public ChildLog() {
        childlogger.info("INFO == INFO");
        childlogger.debug("DEBUG < INFO");
    }
}
