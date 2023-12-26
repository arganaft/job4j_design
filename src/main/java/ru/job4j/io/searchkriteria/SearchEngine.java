package ru.job4j.io.searchkriteria;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class SearchEngine {
    private final Path startDirectory;
    private final String regex;
    private final String type;
    private final String targetDirectory;
    private final Console console;

    public SearchEngine(String[] args, Console console) {
        this.console = System.console();
        ArgsName tempArgs = ArgsName.of(args);
        while (!validateArgs(tempArgs)) {
            tempArgs = ArgsName.of(console.readLine("Введите корректные параметры: ").split(" "));
        }
        this.startDirectory = Paths.get(tempArgs.get("d"));
        this.regex = tempArgs.get("n");
        this.type = tempArgs.get("t");
        this.targetDirectory = tempArgs.get("o");
    }

    private boolean validateArgs(ArgsName argsName) {
        boolean validate = true;
        if (!new File(argsName.get("d")).isDirectory()) {
            console.printf("параметр -d должен содержать директорию");
            validate = false;
        }

        if (argsName.get("d").length() == 0) {
            console.printf("параметр -n должен содержать имя файла, маску, либо регулярное выражение");
            validate = false;
        }

        if (!"mask".equals(argsName.get("t"))
                & !"name".equals(argsName.get("t"))
                & !"regex".equals(argsName.get("t"))) {
            console.printf("параметр -n должен содержать тип поиска:"
                    + " mask искать по маске, name по полному совпадение имени, regex по регулярному выражению");
            validate = false;
        }

        if (!argsName.get("o").matches("^[a-zA-Z0-9]+\\.[a-z]{3}$")) {
            console.printf("параметр -d должен содержать файл");
            validate = false;
        }
        return validate;
    }

    public void writeToFile() throws IOException {
        if ("mask".equals(type)) {
            writeToFile(searchMask());
        } else if ("name".equals(type)) {
            writeToFile(searchName());
        } else if ("regex".equals(type)) {
            writeToFile(searchRegex(regex));
        } else {
            throw new IllegalArgumentException("Ошибка при проверке условий отбора файлов");
        }
    }

    private List<Path> searchMask() throws IOException {
        StringBuilder maskToRegex = new StringBuilder("");
        for (String symbol : regex.split("")) {
            if ("*".equals(symbol)) {
                maskToRegex.append("[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+");
            } else if ("?".equals(symbol)) {
                maskToRegex.append("[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{1}");
            } else if (".".equals(symbol)) {
                maskToRegex.append("\\.");
            } else {
                maskToRegex.append(symbol);
            }
            maskToRegex.append(symbol);
        }
        return searchRegex(maskToRegex.toString());
    }

    private List<Path> searchName() throws IOException {
        return Search.search(startDirectory, p -> regex.equals(p.toFile().getName()));
    }

    private List<Path> searchRegex(String regex) throws IOException {
        Predicate<String> regexFilter = Pattern.compile(regex).asPredicate();
        return Search.search(startDirectory, path -> regexFilter.test(path.toString()));
    }

    private void writeToFile(List<Path> paths) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetDirectory))) {
            for (Path path : paths) {
                writer.write(path.toString());
                console.printf("Добавлен файл: %s\n", path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
