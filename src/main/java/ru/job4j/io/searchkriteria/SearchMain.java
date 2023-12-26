package ru.job4j.io.searchkriteria;

import java.io.Console;
import java.io.IOException;

public class SearchMain {

    public static void main(String[] args) throws IOException {
        Console console = System.console();
        SearchEngine searchEngine = new SearchEngine(args, console);
        searchEngine.writeToFile();
    }


}
