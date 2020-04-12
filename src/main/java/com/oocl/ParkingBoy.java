package com.oocl;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException(ErrorMsg.NO_PARKING_TICKET);
        } else if ((parkingTicket != null) && (this.parkingLot.fetch(parkingTicket) == null)){
            throw new IllegalArgumentException(ErrorMsg.UNRECOGNIZED_PARKING_TICKET);
        } else {
            return this.parkingLot.fetch(parkingTicket);
        }
    }

    public ParkingTicket park(Car car) {
        return this.parkingLot.park(car);
    }
}