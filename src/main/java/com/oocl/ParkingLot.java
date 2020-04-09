package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public final static int DEFAULT_CAPACITY = 10;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<ParkingTicket, Car>();
    private int capacity = DEFAULT_CAPACITY;

    public ParkingLot() {
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        boolean isFull = this.parkingTicketCarMap.size() == this.capacity;
        boolean isParked = this.parkingTicketCarMap.containsValue(car);
        if (isFull || isParked) {
            return null;
        } else {
            ParkingTicket parkingTicket = new ParkingTicket();
            this.parkingTicketCarMap.put(parkingTicket, car);
            return parkingTicket;
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = this.parkingTicketCarMap.remove(parkingTicket);
        return fetchedCar;
    }
}
