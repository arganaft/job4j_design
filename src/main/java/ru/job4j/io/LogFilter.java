package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> res = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            res = in.lines().map(str -> str.split(" ")).
                    filter(arr -> arr[arr.length - 2].equals("404")).
                    map(Arrays::toString).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        log.forEach(System.out::println);

    }
}
