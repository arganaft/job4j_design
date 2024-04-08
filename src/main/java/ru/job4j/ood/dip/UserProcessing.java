package ru.job4j.ood.dip;

public class UserProcessing {
    UserStorage storage;

    public UserProcessing(UserStorage storage) {
        this.storage = storage;
    }

    public void doUserProcess() {
        for (String str : storage.getDatabase()) {
            System.out.println(str);
        }
    }

}
