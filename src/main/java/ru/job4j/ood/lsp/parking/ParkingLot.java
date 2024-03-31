package ru.job4j.ood.lsp.parking;

public interface ParkingLot {


    boolean addVehicle(Vehicle vehicle);
    boolean removeVehicle(Vehicle vehicle);

    Vehicle[] getCarSpace();

    Vehicle[] getTrackSpace();

}
