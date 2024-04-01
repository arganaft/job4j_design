package ru.job4j.ood.lsp.parking;

import com.sun.source.tree.IfTree;
import ru.job4j.set.Set;

import java.util.HashSet;
import java.util.TreeSet;

public class CitiParking implements ParkingLot {

    private Vehicle[] carSpace;
    private Vehicle[] trackSpace;

    private HashSet<Vehicle> vehicleSet = new HashSet<Vehicle>();

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
        boolean isAdd = false;
        if (vehicleSet.contains(vehicle)) {
            return false;
        }
        int freePlace = -1;
        if (vehicle.getSize() > 1) {
            freePlace = getFreePlace(trackSpace);
            if (freePlace != -1) {
                trackSpace[freePlace] = vehicle;
                isAdd = true;
                vehicleSet.add(vehicle);
            } else {
                freePlace = carSpaceCheck(vehicle.getSize());
                if (freePlace  != -1) {
                    for (int i = freePlace; i < vehicle.getSize(); i++) {
                        carSpace[i] = vehicle;
                    }
                    isAdd = true;
                    vehicleSet.add(vehicle);
                }
            }
        } else {
            freePlace = getFreePlace(carSpace);
            if (freePlace != -1) {
                carSpace[freePlace] = vehicle;
                isAdd = true;
                vehicleSet.add(vehicle);
            }
        }
        return isAdd;
    }

    @Override
    public boolean removeVehicle(Vehicle vehicle) {
        if (!vehicleSet.contains(vehicle)) {
            return false;
        }
        return (vehicle.getSize() > 1) ? (removeFromSpace(trackSpace, vehicle) || removeTruckFromCarSpace(vehicle))
                : (removeFromSpace(carSpace, vehicle));
    }

    private boolean removeFromSpace(Vehicle[] space, Vehicle vehicle) {
        boolean isRemove = false;
        for (int i = 0; i < space.length; i++) {
            if (space[i] == vehicle) {
                space[i] = null;
                isRemove = true;
                break;
            }
        }
        return isRemove;
    }

    private boolean removeTruckFromCarSpace(Vehicle truck) {
        boolean isRemove = false;
        for (int i = 0; i < carSpace.length; i++) {
            if (carSpace[i] == truck) {
                for (int j = 0; j < truck.getSize(); j++) {
                    carSpace[i++] = null;
                }
                isRemove = true;
                break;
            }
        }
        return isRemove;
    }

    private int carSpaceCheck(int truckSize) {
        int place = -1;
        for (int i = 0; i < carSpace.length; i++) {
            if (carSpace[i] == null) {
                place = i;
                for (int j = 0; j < truckSize; j++) {
                    if (carSpace[i++] != null) {
                        place = -1;
                        break;
                    }
                }
            }
            if (place != -1) {
                break;
            }
        }
        return place;
    }

    private int getFreePlace(Vehicle[] space) {
        int freePlace = -1;
        for (int i = 0; i < space.length; i++) {
            if (space[i] == null) {
                freePlace = i;
                break;
            }
        }
        return freePlace;
    }


    public static void main(String[] args) {
        Vehicle[] vehicle = new Vehicle[10];
        for (int i = 0; i < vehicle.length; i++) {
            System.out.println(i);
        }

    }

}
