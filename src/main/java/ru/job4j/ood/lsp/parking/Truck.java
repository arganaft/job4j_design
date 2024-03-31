package ru.job4j.ood.lsp.parking;

import java.util.LinkedList;
import java.util.List;

public class Truck implements Vehicle {
    private int size;
    private List<Vehicle> truckTrailer = new LinkedList<>();

    public Truck(int size) {
        if (size < 20) {
            throw new IllegalArgumentException("размер грузовика должен быть не отрицательным больше 2х ");
        }
        for (int i = 0; i < size; i++) {
            truckTrailer.add(new TruckTrailer(this));
        }
        this.size = size;
    }

    public List<Vehicle> getTruckTrailer() {
        return truckTrailer;
    }

    @Override
    public int getSize() {
        return size;
    }

    static class TruckTrailer implements Vehicle {
        private int size;
        private Vehicle truck;

        public TruckTrailer(Vehicle truck) {
            this.size = 1;
            this.truck = truck;
        }

        public Vehicle getTruck() {
            return truck;
        }

        @Override
        public int getSize() {
            return size;
        }
    }
}
