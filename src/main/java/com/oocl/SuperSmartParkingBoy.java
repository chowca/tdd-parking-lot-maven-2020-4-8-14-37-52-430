package com.oocl;

import java.util.Comparator;

public class SuperSmartParkingBoy extends SmartParkingBoy {
    public SuperSmartParkingBoy(ParkingLot... ParkingLots) {
        super(ParkingLots);
    }

    @Override
    public ParkingLot getSelectedParkingLot() {
        return this.getParkingLots()
                .stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .sorted(Comparator.comparing(ParkingLot::getCapacity).reversed())
                .max(Comparator.comparingDouble(ParkingLot::getAvailablePositionRate))
                .orElse(null);
    }
}
