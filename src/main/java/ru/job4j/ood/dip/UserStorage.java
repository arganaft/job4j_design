package ru.job4j.ood.dip;


import java.util.ArrayList;

public class UserStorage {
    private ArrayList<String> database;

    public UserStorage() {
        this.database = new ArrayList<>();
    }

    public ArrayList<String> getDatabase() {
        return database;
    }
}
