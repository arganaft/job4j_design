package ru.job4j.ood.lsp.distributor;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {
    @Test
    public void whenPartialExpirationCoverageReturnsException() {
        List<AbstractStore> stores = new ArrayList<>();
        AbstractStore warehouse = new Warehouse(food -> food.expireDetect() < 24);
        AbstractStore shop = new Shop(
                food -> (food.expireDetect() >= 25 && food.expireDetect() < 100),
                food -> food.expireDetect() >= 75, 20);
        AbstractStore trash = new Trash(food -> food.expireDetect() >= 100);
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        assertThatThrownBy(() -> new ControlQuality(stores))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Хранилища перекрывают не весь диапазон срока годности продукта");
    }

    @Test
    public void whenOverlappingStoresReturnsException() {
        List<AbstractStore> stores = new ArrayList<>();
        AbstractStore warehouse = new Warehouse(food -> food.expireDetect() < 26);
        AbstractStore shop = new Shop(
                food -> (food.expireDetect() >= 25 && food.expireDetect() < 100),
                food -> food.expireDetect() >= 75, 20);
        AbstractStore trash = new Trash(food -> food.expireDetect() >= 100);
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        assertThatThrownBy(() -> new ControlQuality(stores))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Несколько хранилищ перекрывают один диапазон срока годности продукта");
    }

    @Test
    public void whenDistributeReturnsValidValue() {
        List<AbstractStore> stores = new ArrayList<>();
        AbstractStore warehouse = new Warehouse(food -> food.expireDetect() < 25);
        AbstractStore shop = new Shop(
                food -> (food.expireDetect() >= 25 && food.expireDetect() < 100),
                food -> food.expireDetect() >= 75, 20);
        AbstractStore trash = new Trash(food -> food.expireDetect() >= 100);
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        LocalDateTime now = LocalDateTime.now();
        Food food1 = new Food("food", now.minusDays(27), now.plusDays(73), 50);
        Food food2 = new Food("food", now.minusDays(30), now, 50);
        controlQuality.distribute(food1);
        controlQuality.distribute(food2);
        assertThat(shop.getStore()).contains(food1).doesNotContain(food2);
    }

    @Test
    public void whenRedistributeReturnsValidValue() {
        List<AbstractStore> stores = new ArrayList<>();
        AbstractStore warehouse = new Warehouse(food -> food.expireDetect() < 25);
        AbstractStore shop = new Shop(
                food -> (food.expireDetect() >= 25 && food.expireDetect() < 100),
                food -> food.expireDetect() >= 75, 20);
        AbstractStore trash = new Trash(food -> food.expireDetect() >= 100);
        stores.add(warehouse);
        stores.add(shop);
        stores.add(trash);
        ControlQuality controlQuality = new ControlQuality(stores);
        LocalDateTime now = LocalDateTime.now();
        Food food1 = new Food("food", now.minusDays(27), now.plusDays(73), 50);
        Food food2 = new Food("food", now.minusDays(30), now, 50);
        warehouse.add(food1);
        shop.add(food2);
        controlQuality.redistribute();
        assertThat(shop.getStore()).contains(food1).doesNotContain(food2);
    }

}