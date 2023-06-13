package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(line -> !line.isBlank() && !line.startsWith("#")).
                    filter(this::validLine).
                    map(line -> line.split("=", 2)).
                    forEach(arr -> values.putIfAbsent(arr[0], arr[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validLine(String line) {
        if (!line.contains("=")
                || line.startsWith("=")
                || line.indexOf("=") == line.length() - 1) {
            throw new IllegalArgumentException("Обнаружено нарушение шаблона в строке - %s".formatted(line));
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        String path = "./data/Pair_With_Pattern_Violation.properties";
        Config config = new Config(path);
        config.load();
        config.value("name");
    }

}