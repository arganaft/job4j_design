package ru.job4j.ood;


import org.slf4j.Logger;

public class Log4JUse {
    private final Logger LOG;

    public Log4JUse(Logger logger) {
        this.LOG = logger;
    }

    public void printDebug(String message) {
        LOG.debug(message);
    }
    public void printTrace(String message) {
        LOG.trace(message);
    }
    public void printError(String message) {
        LOG.error(message);
    }
    public void printInfo(String message) {
        LOG.info(message);
    }
    public void printWarn(String message) {
        LOG.warn(message);
    }

}
