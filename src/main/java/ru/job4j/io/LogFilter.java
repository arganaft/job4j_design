package ru.job4j.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> res = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            res = in.lines().map(str -> str.split(" ")).
                    filter(arr -> "404".equals(arr[arr.length - 2])).
                    map(Arrays::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void save(List<String> log, String file) {
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        log.forEach(System.out::println);
        save(log, "data/404.txt");

    }
}
