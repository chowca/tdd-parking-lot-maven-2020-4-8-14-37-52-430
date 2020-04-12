package com.oocl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingLot selectedParkingLot;


    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
        this.selectedParkingLot = this.parkingLots.stream().findFirst().orElse(null);

    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException(ErrorMsg.NO_PARKING_TICKET);
        }else{
            this.selectedParkingLot = parkingTicket.getParkingLot();
        }
        if ((parkingTicket != null) && (!this.selectedParkingLot.isTicketFound(parkingTicket))) {
            throw new IllegalArgumentException(ErrorMsg.UNRECOGNIZED_PARKING_TICKET);
        } else {
            return this.selectedParkingLot.fetch(parkingTicket);
        }
    }

    public ParkingTicket park(Car car) {
        this.selectedParkingLot = this.parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().orElse(null);
        if (this.selectedParkingLot == null) {
            throw new IllegalArgumentException(ErrorMsg.NOT_ENOUGH_POSITION);
        } else {
            return this.selectedParkingLot.park(car);
        }
    }
}