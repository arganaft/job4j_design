package ru.job4j.logger;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MyLoggerTest {
    @Test
    void debugMessageToList() {
        List<String> storage = new LinkedList<>();
        LogSaver saver = new ListSaver(storage);
        LoggerInterface logger = new MyLogger(saver);
        String message = "my debug message";
        String expectedString = String.format("%s DEBUG:", message);
        logger.debug(message);
        Assertions.assertThat(storage)
                .extracting(s -> s.substring(0, expectedString.length()))
                .contains(expectedString);
    }

    @Test
    void debugMessageToConsole() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));

        LogSaver saver = new ConsoleSaver();
        LoggerInterface logger = new MyLogger(saver);
        String message = "my debug message";
        String expectedString = String.format("%s DEBUG:", message);
        logger.debug(message);
        String consoleOutput = outputStreamCaptor.toString().trim();
        System.setOut(originalOut);
        Assertions.assertThat(expectedString)
                .contains(expectedString);
    }

    @Test
    void debugMessageToLog4j() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStreamCaptor));

        LoggerInterface logger = new Log4jLogger();
        String message = "my debug message";
        String expectedString = String.format("%s DEBUG:", message);
        logger.debug(message);
        String consoleOutput = outputStreamCaptor.toString().trim();
        System.setOut(originalOut);
        Assertions.assertThat(expectedString)
                .contains(expectedString);
    }

}