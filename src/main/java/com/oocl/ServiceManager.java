package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceManager {
    private List<ParkingBoy> parkingBoys = new ArrayList<>();
    private ParkingBoy selectedParkingBoy;

    public void assignParkingBoyInList(ParkingBoy... parkingBoys) {
        this.parkingBoys.addAll(Arrays.asList(parkingBoys));
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public ParkingTicket parkByParkingBoy(Car car) {
        selectedParkingBoy = parkingBoys
                .stream()
                .filter(parkingBoy -> parkingBoy.getSelectedParkingLot() != null)
                .findFirst()
                .orElse(null);
        return selectedParkingBoy.park(car);
    }
}
