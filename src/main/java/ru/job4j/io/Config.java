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
            read.lines().filter(Config::validLine).
                    map(line -> line.split("=", 2)).
                    forEach(arr -> values.putIfAbsent(arr[0], arr[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean validLine(String line) {
        if (line.length() == 0) {
            return false;
        }
        if (!line.contains("=") || line.startsWith("=") || line.split("=").length < 2) {
            throw new IllegalArgumentException();
        }
        return !line.startsWith("#");
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
        System.out.println(new Config("data/app.properties"));
    }

}