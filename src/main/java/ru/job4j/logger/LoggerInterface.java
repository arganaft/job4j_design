package ru.job4j.logger;

public interface LoggerInterface {
    void debug(String message);
    void info(String message);
    void warn(String message);
    void error(String message);
}