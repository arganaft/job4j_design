package ru.job4j.ood.lsp.distributor;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    public void whenTakeAwayOnRuleReturnsValidValue() {
        LocalDateTime now = LocalDateTime.now();
        Food food1 = new Food("food", now.minusDays(27), now.plusDays(73), 50);
        Food food2 = new Food("food", now.minusDays(30), now, 50);
        AbstractStore shop = new Shop(
                food -> (food.expireDetect() >= 25 && food.expireDetect() < 100),
                food -> food.expireDetect() >= 75, 20);
        shop.add(food1);
        shop.add(food2);
        assertThat(shop.takeAwayIfViolatesRule()).contains(food2).doesNotContain(food1);
    }

    @Test
    public void whenAddNullReturnsException() {
        AbstractStore shop = new Shop(
                food -> (food.expireDetect() >= 25 && food.expireDetect() < 100),
                food -> food.expireDetect() >= 75, 20);
        assertThatThrownBy(() -> shop.add(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void whenCreateWithNullReturnsException() {
        assertThatThrownBy(() -> new Shop(null, null, 30)).isInstanceOf(NullPointerException.class);
    }

}