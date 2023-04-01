package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkThrownHasMessage() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty()
                .isNotBlank();
    }

    @Test
    void checkNamesIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkNamesNotContainASymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"key1*value1", "key2*value2", "key3*value3"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names[0])
                .hasMessageContaining("not contain the symbol");
    }

    @Test
    void checkNamesNotContainAValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"key1value1=", "key2value2=", "key3value3="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(names[0])
                .hasMessageContaining("does not contain a value");
    }
}