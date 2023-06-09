package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .allSatisfy(str -> assertThat(str)
                        .isNotEmpty()
                        .isNotBlank()
                        .doesNotStartWithIgnoringCase("any"))
                .anySatisfy(str -> assertThat(str)
                        .contains("ur")
                        .startsWithIgnoringCase("FO"))
                .element(1)
                .isNotEqualTo("three")
                .isEqualTo("second");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five", "first");
        assertThat(set).hasSize(5)
                .isNotEmpty()
                .isNotNull()
                .anyMatch(str -> str.length() > 3)
                .containsAnyOf("one", "1", "first")
                .containsExactlyInAnyOrder("first", "second", "three", "four", "five");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .isNotEmpty()
                .isNotNull()
                .containsKeys("first", "three", "five")
                .containsValues(1, 2, 3)
                .containsEntry("three", 2)
                .doesNotContainEntry("zero", 0);
    }
}