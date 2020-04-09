package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<ParkingTicket,Car> parkingTicketCarMap = new HashMap<ParkingTicket, Car>();

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        this.parkingTicketCarMap.put(parkingTicket,car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = this.parkingTicketCarMap.remove(parkingTicket);
        return fetchedCar;
    }
}
