package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 5, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfEven() {
        ListUtils.removeIf(input, integer -> integer % 2 == 0);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveIfOdd() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.removeIf(input, integer -> integer % 2 == 1);
        assertThat(input).hasSize(1).containsSequence(2);
    }

    @Test
    void whenRemoveIfmoreThan2() {
        ListUtils.removeIf(input, integer -> integer > 2);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenReplaceIfOdd() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.replaceIf(input, integer -> integer % 2 == 1, 2);
        assertThat(input).hasSize(3).containsSequence(2, 2, 2);
    }

    @Test
    void whenReplaceIfNegative() {
        ListUtils.addBefore(input, 1, -2);
        ListUtils.replaceIf(input, integer -> integer < 0, 0);
        assertThat(input).hasSize(3).containsSequence(1, 0, 3);
    }

    @Test
    void whenRemoveAllInList() {
        ListUtils.addBefore(input, 1, 2);
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.removeAll(input, list);
        assertThat(input).hasSize(1).containsSequence(2);
    }

    @Test
    void whenRemoveAllInListOf5() {
        ListUtils.addBefore(input, 1, 2);
        List<Integer> list = new ArrayList<>(Arrays.asList(5));
        ListUtils.removeAll(input, list);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }
}