package ru.job4j.ood.lsp.parking;

public class CitiParking implements ParkingLot {

    private Vehicle[] carSpace;
    private Vehicle[] trackSpace;

    public CitiParking(int carSpace, int trackSpace) {
        if (carSpace < 0 || trackSpace < 0) {
            throw new IllegalArgumentException("Размер парковки не может быть отрицательной");
        }
        this.carSpace = new Vehicle[carSpace];
        this.trackSpace = new Vehicle[trackSpace];
    }

    @Override
    public Vehicle[] getCarSpace() {
        return carSpace;
    }

    @Override
    public Vehicle[] getTrackSpace() {
        return trackSpace;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean removeVehicle(Vehicle vehicle) {
        return false;
    }

}
