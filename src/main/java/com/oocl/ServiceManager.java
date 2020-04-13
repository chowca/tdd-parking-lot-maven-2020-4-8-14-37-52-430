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
        if (selectedParkingBoy == null) {
            throw new IllegalArgumentException(ErrorMsg.NOT_ENOUGH_POSITION);
        } else {
            return selectedParkingBoy.park(car);
        }
    }

    public Car fetchByParkingTicket(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException(ErrorMsg.NO_PARKING_TICKET);
        } else {
            selectedParkingBoy = parkingBoys
                    .stream()
                    .filter(parkingBoy -> parkingBoy.getParkingLots()
                            .stream()
                            .filter(parkingLot -> parkingLot.isTicketFound(parkingTicket))
                            .findFirst().orElse(null) != null)
                    .findFirst()
                    .orElse(null);
        }
        if (selectedParkingBoy == null) {
            throw new IllegalArgumentException(ErrorMsg.UNRECOGNIZED_PARKING_TICKET);
        } else {
            return selectedParkingBoy.fetch(parkingTicket);
        }
    }
}
