package ru.job4j.ood.lsp.distributor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ControlQuality {

    private List<AbstractStore> stores;

    public ControlQuality(List<AbstractStore> stores) {
        byte expireDetect = expireDetect(stores);
        if (expireDetect < 1) {
            throw new IllegalArgumentException("Хранилища перекрывают не весь диапазон срока годности продукта");
        }
        if (expireDetect > 1) {
            throw new IllegalArgumentException("Несколько хранилищ перекрывают один диапазон срока годности продукта");
        }
        this.stores = stores;
    }

    private byte expireDetect(List<AbstractStore> stores) {
        LocalDateTime now = LocalDateTime.now();
        byte expireDetect = 0;
        for (int i = 0; i <= 100; i++) {
            for (AbstractStore store : stores) {
                if (store.admissionRule.test(new Food("food", now.minusDays(i), now.plusDays(100 - i), 50))) {
                    expireDetect++;
                }
            }
            if (expireDetect != 1) {
                break;
            }
            if (i < 100) {
                expireDetect = 0;
            }
        }
        return expireDetect;
    }

    public  void redistribute() {
        for (AbstractStore store : stores) {
            store.takeAwayIfViolatesRule().forEach(this::distribute);
        }
    }

    public void distribute(Food food) {
        boolean isAdded = false;
        for (AbstractStore store : stores) {
            if (store.admissionRule.test(food)) {
                store.add(food);
                isAdded = true;
                break;
            }
        }
        if (!isAdded) {
            throw new IllegalStateException("Этот товар не может быть принят ни на один склад");
        }
    }

    public static void main(String[] args) {
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

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            LocalDateTime createDate = LocalDateTime.now().minusDays(random.nextInt(20));
            LocalDateTime expiryDate = LocalDateTime.now().plusDays(random.nextInt(20));
            Food food = new Food(String.format("food %d", i), createDate, expiryDate, random.nextInt(10, 100));
            controlQuality.distribute(food);
        }


        for (AbstractStore store : stores) {
            System.out.println(store.getClass().getSimpleName());
            System.out.println(store.getStore());
        }
    }

}
