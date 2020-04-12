package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity = Config.DEFAULT_CAPACITY;
    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<ParkingTicket, Car>();
    

    public ParkingLot() {
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingTicket park(Car car) {
        if (isFull() || isParked(car)) {
            return null;
        } else {
            ParkingTicket parkingTicket = new ParkingTicket(this);
            this.parkingTicketCarMap.put(parkingTicket, car);
            return parkingTicket;
        }
    }

    public boolean isParked(Car car) {
        return this.parkingTicketCarMap.containsValue(car);
    }

    public boolean isFull() {
        return this.parkingTicketCarMap.size() == this.capacity;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = this.parkingTicketCarMap.remove(parkingTicket);
        return fetchedCar;
    }
}
