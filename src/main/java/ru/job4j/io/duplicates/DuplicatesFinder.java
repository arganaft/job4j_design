package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), duplicatesVisitor);
        if (!duplicatesVisitor.getDuplicates().isEmpty()) {
            System.out.println("Найдены дупликаты");
            duplicatesVisitor.getDuplicates().forEach(System.out::println);
        } else {
            System.out.println("Дубликаты не найдены");
        }

    }
}
