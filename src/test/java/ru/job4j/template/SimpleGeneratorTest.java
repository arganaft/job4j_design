package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class SimpleGeneratorTest {

    @Test
    void whenProduceValidKeyThenGetValidStrig() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        Generator generator = new SimpleGenerator();
        assertThat(generator.produce(template, args)).isEqualTo("\"I am a Petr Arsentev, Who are you? \"");
    }

    @Test
    void whenProduceInvalidKeyInStringThenGetException() {
        String template = "I am a ${name}, Who are ${profession}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        Generator generator = new SimpleGenerator();
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenProduceInvalidKeyInMapThenGetException() {
        String template = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("profession", "you");
        Generator generator = new SimpleGenerator();
        assertThatThrownBy(() -> generator.produce(template, args))
                .isInstanceOf(IllegalArgumentException.class);
    }
}