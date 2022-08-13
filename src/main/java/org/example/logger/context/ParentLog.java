package org.example.logger.context;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.example.logger.context.subfoder.ChildLog;
import org.slf4j.LoggerFactory;


public class ParentLog {

    /**
     * All loggers are descendants of the predefined root logger, the App logger will inherit the configuration.
     * By default il wil takes the root level="debug" from logback.xml
     */
    static final Logger parentLogger = (Logger) LoggerFactory.getLogger("org.example.logger.context");

    public ParentLog() {
        /**
         * we can set log level programmatically or with xml configuration.
         * <logger name="org.example.logger.context" level="INFO" />
         * <logger name="org.example.logger.context.subfolder" level="WARN" />
         * The setLevel method is not available with slf4j, that's why we use Logger from logback directly.
         */
        parentLogger.setLevel(Level.INFO);
        parentLogger.warn("This message is logged because WARN > INFO.");
        parentLogger.debug("This message is not logged because DEBUG < INFO.");
    }

    public static void main(String[] args) {
        ParentLog parentlog = new ParentLog();
        ChildLog childlog = new ChildLog();

    }
}