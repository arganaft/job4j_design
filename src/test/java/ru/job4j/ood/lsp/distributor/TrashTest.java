package ru.job4j.ood.lsp.distributor;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class TrashTest {
    @Test
    public void whenTakeAwayOnRuleReturnsValidValue() {
        LocalDateTime now = LocalDateTime.now();
        Food food1 = new Food("food", now.minusDays(10), now, 50);
        Food food2 = new Food("food", now.minusDays(30), now.plusDays(1), 50);
        AbstractStore trash = new Trash(food -> food.expireDetect() >= 100);
        trash.add(food1);
        trash.add(food2);
        trash.takeAwayIfViolatesRule();
        assertThat(trash.getStore()).contains(food1).doesNotContain(food2);
    }

    @Test
    public void whenAddNullReturnsException() {
        AbstractStore trash = new Trash(food -> food.expireDetect() >= 100);
        assertThatThrownBy(() -> trash.add(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void whenCreateWithNullReturnsException() {
        assertThatThrownBy(() -> new Trash(null)).isInstanceOf(NullPointerException.class);
    }

}