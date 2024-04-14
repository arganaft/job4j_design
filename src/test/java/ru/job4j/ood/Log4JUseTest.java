package ru.job4j.ood;

import org.apache.log4j.PropertyConfigurator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;

class Log4JUseTest {
    private static final Logger LOG = LoggerFactory.getLogger(Log4JUse.class.getName());
    Log4JUse log4JUse = new Log4JUse(LOG);



    @Test
    public void testDebugLogging() throws IOException {
        log4JUse.printDebug("This is a DEBUG message logged to debug.txt");
        assertThat(Files.readAllLines(Paths.get("logs/debug.txt"))).anySatisfy(line -> assertThat(line)
                .contains("DEBUG ru.job4j.ood.Log4JUse:printDebug"));
    }

    @Test
    public void testErrorLogging() throws IOException {
        log4JUse.printError("This is an ERROR message logged to error.log");
        assertThat(Files.readAllLines(Paths.get("logs/error.log"))).anySatisfy(line -> assertThat(line)
                        .contains("ERROR ru.job4j.ood.Log4JUse:printError"));
        assertThat(Files.readAllLines(Paths.get("logs/error.log"))).anySatisfy(line -> assertThat(line)
                .doesNotContain("DEBUG ru.job4j.ood.Log4JUse:printDebug"));
    }

}