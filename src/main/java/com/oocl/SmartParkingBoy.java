package com.oocl;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot... ParkingLots) {
        super(ParkingLots);
    }

    @Override
    public ParkingLot getSelectedParkingLot() {
        return this.getParkingLots().stream().filter(parkingLot -> !parkingLot.isFull()).max(Comparator.comparing(ParkingLot::getCapacity)).orElse(null);
    }
}
