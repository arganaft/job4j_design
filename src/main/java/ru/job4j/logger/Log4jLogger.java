package ru.job4j.logger;

import org.apache.log4j.LogManager;

public class Log4jLogger implements LoggerInterface {
    private final org.apache.log4j.Logger log = LogManager.getLogger(Log4jLogger.class.getName());

    @Override
    public void debug(String message) {
        log.debug("debug message");
    }

    @Override
    public void info(String message) {
        log.info("info message");
    }

    @Override
    public void warn(String message) {
        log.warn("warn message");
    }

    @Override
    public void error(String message) {
        log.error("error message");
    }
}
