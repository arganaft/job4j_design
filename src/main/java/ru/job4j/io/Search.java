package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (!Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException("Root argument is not Directory. Usage  ROOT_FOLDER.");
        }
        if (args.length ==1) {
            throw new IllegalArgumentException("Не указанно расширение файла");
        }
        if (!args[1].startsWith(".") || args[1].length() > 4) {
            throw new IllegalArgumentException("Расширение файла должно начинаться с \".\" и " +
                    "содержать от 1го до 3х символов латинского алфавита ");
        }
    }
}
