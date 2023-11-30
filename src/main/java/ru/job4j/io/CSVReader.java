package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        ArrayList<ArrayList<String>> source = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path"))).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                source.add(new ArrayList<>(Arrays
                        .stream(scanner.next()
                                .split(argsName.get("delimiter")))
                        .collect(Collectors.toList())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> filter = new ArrayList<>(List.of(argsName.get("filter").split(",")));
        ArrayList<Integer> filterList = new ArrayList<>();
        for (int i = 0; i < filter.size(); i++) {
            for (int j = 0; j < source.get(0).size(); j++) {
                if (source.get(0).get(j).equals(filter.get(i))) {
                    filterList.add(j);
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
            for (ArrayList<String> line : source) {
                StringJoiner finalLine = new StringJoiner(argsName.get("delimiter"));
                for (Integer filterElement : filterList) {
                    finalLine.add(line.get(filterElement));
                }
                writer.write(finalLine.toString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArgsName argValidation(String[] args) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Для работы необходимы четыре параметра");
        }
        ArgsName argsName = ArgsName.of(args);
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Необходим исходный файл с расширением - csv");
        }
        if (argsName.get("delimiter").isEmpty()) {
            throw new IllegalArgumentException("Не указан разделитель строк");
        }
        if (!argsName.get("out").endsWith(".csv")) {
            throw new IllegalArgumentException("Необходимо указать файл для записи с расширением - csv");
        }
        if (argsName.get("filter").isEmpty()) {
            throw new IllegalArgumentException("Не указан список для фильтрации столбцов");
        }
        return argsName;
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = argValidation(args);
        handle(argsName);
    }
}
