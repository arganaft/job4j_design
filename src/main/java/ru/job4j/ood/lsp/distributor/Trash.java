package ru.job4j.ood.lsp.distributor;

import java.util.function.Predicate;

public class Trash extends AbstractStore {

    public Trash(Predicate<Food> admissionRule) {
        super(admissionRule);
    }

    public void disposeTrash() {
        store.clear();
    }
}
