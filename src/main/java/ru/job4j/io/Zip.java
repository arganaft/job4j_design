package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getAbsolutePath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(String.valueOf(source)))) {
                    zip.write(out.readAllBytes());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void packSingleFile(File source, File target) {
        System.out.println("     " + source.getName());
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target, true)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        SearchFiles searcher = new SearchFiles(p -> true);
        Files.walkFileTree(
                Paths.get("C:\\projects\\job4j_design\\"),
                searcher
        );
        zip.packFiles(
                searcher.getPaths(),
                new File("./main.zip")
        );
    }
}