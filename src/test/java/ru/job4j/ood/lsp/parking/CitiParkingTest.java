package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class CitiParkingTest {
    @Test
    public void whenAdd1Car1TrackReturnsValidValue() {
        Vehicle car = new Car();
        Vehicle truck = new Truck(2);
        ParkingLot parking = new CitiParking(4, 0);
        parking.addVehicle(car);
        parking.addVehicle(truck);
        assertThat(parking.getCarSpace()).contains(car).contains(truck);
    }

    @Test
    public void whenAdd2TrackReturnsValidValue() {
        Vehicle truck1 = new Truck(2);
        Vehicle truck2 = new Truck(2);
        ParkingLot parking = new CitiParking(2, 1);
        parking.addVehicle(truck1);
        parking.addVehicle(truck2);
        assertThat(parking.getCarSpace()).contains(truck2);
    }

    @Test
    public void whenAddAndRemove2TrackReturnsValidValue() {
        Vehicle truck1 = new Truck(2);
        Vehicle truck2 = new Truck(2);
        ParkingLot parking = new CitiParking(2, 1);
        parking.addVehicle(truck1);
        parking.addVehicle(truck2);
        parking.removeVehicle(truck1);
        assertThat(parking.getTrackSpace()).isEmpty();
    }

}