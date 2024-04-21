package ru.job4j.logger;

public class MyLogger implements LoggerInterface {
    LogSaver saver;

    public MyLogger(LogSaver saver) {
        this.saver = saver;
    }

    @Override
    public void debug(String message) {
        saver.save(String.format("%s DEBUG: %s", message, System.currentTimeMillis()));
    }

    @Override
    public void info(String message) {
        saver.save(String.format("%s INFO: %s", message, System.currentTimeMillis()));
    }

    @Override
    public void warn(String message) {
        saver.save(String.format("%s WARN: %s", message, System.currentTimeMillis()));
    }

    @Override
    public void error(String message) {
        saver.save(String.format("%s ERROR: %s", message, System.currentTimeMillis()));
    }
}
