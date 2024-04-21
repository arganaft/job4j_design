package ru.job4j.logger;

import java.util.List;

public class ListSaver implements LogSaver {
    private List<String> storage;

    public ListSaver(List<String> storage) {
        this.storage = storage;
    }

    @Override
    public void save(String message) {
        storage.add(message);
    }

    public List<String> getStorage() {
        return storage;
    }
}
