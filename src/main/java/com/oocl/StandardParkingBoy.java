package com.oocl;

public interface StandardParkingBoy {
    Car fetch(ParkingTicket parkingTicket);

    ParkingTicket park(Car car);

    ParkingLot getSelectedParkingLot();
}
