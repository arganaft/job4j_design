package ru.job4j.io;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        Arrays.stream(Objects.requireNonNull(file.listFiles())).
                filter(el -> !el.isDirectory()).
                forEach(el -> System.out.printf("File %s - has size :  %s byte\n", el, el.length()));
    }
}
