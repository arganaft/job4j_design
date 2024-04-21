package ru.job4j.logger;

public class ConsoleSaver implements LogSaver {
    @Override
    public void save(String message) {
        System.out.println(message);
    }
}
