package ru.job4j.ood.lsp.parking;

import java.util.LinkedList;
import java.util.List;

public class Truck implements Vehicle {
    private int size;

    public Truck(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("размер грузовика должен быть не отрицательным больше 2х ");
        }
        this.size = size;
    }


    @Override
    public int getSize() {
        return size;
    }
}
