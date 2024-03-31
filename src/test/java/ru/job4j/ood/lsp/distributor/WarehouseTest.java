package ru.job4j.ood.lsp.distributor;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {
    @Test
    public void whenTakeAwayOnRuleReturnsValidValue() {
        LocalDateTime now = LocalDateTime.now();
        Food food1 = new Food("food", now.minusDays(10), now.plusDays(90), 50);
        Food food2 = new Food("food", now.minusDays(30), now.plusDays(1), 50);
        AbstractStore warehouse = new Warehouse(food -> food.expireDetect() < 25);
        warehouse.add(food1);
        warehouse.add(food2);
        warehouse.takeAwayIfViolatesRule();
        assertThat(warehouse.getStore()).contains(food1).doesNotContain(food2);
    }

    @Test
    public void whenAddNullReturnsException() {
        AbstractStore warehouse = new Warehouse(food -> food.expireDetect() < 25);
        assertThatThrownBy(() -> warehouse.add(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void whenCreateWithNullReturnsException() {
        assertThatThrownBy(() -> new Warehouse(null)).isInstanceOf(NullPointerException.class);
    }

}