package ru.job4j.ood.lsp.distributor;

import java.util.Comparator;
import java.util.function.Predicate;

public class Warehouse extends AbstractStore {

    public Warehouse(Predicate<Food> admissionRule) {
        super(admissionRule);
    }

    protected void sort(Comparator<Food> sorter) {
        getStore().sort(sorter);
    }
}
