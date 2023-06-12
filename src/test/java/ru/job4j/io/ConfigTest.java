package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithEmptyLine() {
        String path = "./data/Pair_With_Empty_Line.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithPatternViolation() {
        String path = "./data/Pair_With_Pattern_Violation.properties";
        Config config = new Config(path);
        assertThatThrownBy(() -> {
            config.load();
            config.value("name");
        }).isInstanceOf(IllegalArgumentException.class).message().contains("surname=");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/Pair_With_Comment.properties";
        Config config = new Config(path);
        assertThat(config.value("name")).isNull();
    }

    @Test
    void whenPairWithMoreThan2Separators() {
        String path = "./data/Pair_With_More_Than_2_Separators.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr= Arsen=tev");
    }

    @Test
    void whenKeyNotFound() {
        String path = "./data/Pair_With_More_Than_2_Separators.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("n77ame")).isNull();
    }
}