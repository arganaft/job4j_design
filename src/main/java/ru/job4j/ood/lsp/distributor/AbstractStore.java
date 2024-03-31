package ru.job4j.ood.lsp.distributor;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractStore {
    protected List<Food> store = new LinkedList<>();
    protected Predicate<Food> admissionRule;

    public AbstractStore(Predicate<Food> admissionRule) {
        if (admissionRule == null) {
            throw new NullPointerException();
        }
        this.admissionRule = admissionRule;
    }

    protected void add(Food food) {
        if (food == null) {
            throw new NullPointerException();
        }
        store.add(food);
    }

    protected boolean addAll(List<Food> foods) {
        return store.addAll(foods);
    }

    protected List<Food> takeAwayIfViolatesRule() {
        List<Food> tempstore = new LinkedList<>();
        for (Food food : store) {
            if (!admissionRule.test(food)) {
                tempstore.add(food);
            }
        }
        store.removeAll(tempstore);
        return tempstore;
    }

    protected List<Food> getStore() {
        return store;
    }

}
