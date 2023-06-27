package ru.job4j.io.duplicates;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, ArrayList<Path>> fileSet = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.toFile().getName());
        fileSet.putIfAbsent(fileProperty, new ArrayList<>());
        fileSet.get(fileProperty).add(file);
        return super.visitFile(file, attrs);
    }

    public ArrayList<Path> getDuplicates() {
        ArrayList<Path> dublicates = new ArrayList<>();
        fileSet.values().stream().filter(paths -> paths.size() > 1).forEach(dublicates::addAll);
        return dublicates;
    }
}
