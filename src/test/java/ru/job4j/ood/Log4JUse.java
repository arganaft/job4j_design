package ru.job4j.ood;


import org.slf4j.Logger;

public class Log4JUse {
    private final Logger log;

    public Log4JUse(Logger logger) {
        this.log = logger;
    }

    public void printDebug(String message) {
        log.debug(message);
    }
    public void printTrace(String message) {
        log.trace(message);
    }
    public void printError(String message) {
        log.error(message);
    }
    public void printInfo(String message) {
        log.info(message);
    }
    public void printWarn(String message) {
        log.warn(message);
    }

}
