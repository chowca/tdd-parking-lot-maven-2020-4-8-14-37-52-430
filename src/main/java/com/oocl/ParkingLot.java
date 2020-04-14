package com.oocl;

import com.constant.Config;

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

    public int getCapacity() {
        return capacity;
    }

    public ParkingTicket park(Car car) {
        if (isFull() || isCarFound(car)) {
            return null;
        } else {
            ParkingTicket parkingTicket = new ParkingTicket(this);
            this.parkingTicketCarMap.put(parkingTicket, car);
            return parkingTicket;
        }
    }

    public boolean isCarFound(Car car) {
        return this.parkingTicketCarMap.containsValue(car);
    }

    public boolean isTicketFound(ParkingTicket parkingTicket) {
        return this.parkingTicketCarMap.containsKey(parkingTicket);
    }

    public boolean isFull() {
        return this.parkingTicketCarMap.size() == this.capacity;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car fetchedCar = this.parkingTicketCarMap.remove(parkingTicket);
        return fetchedCar;
    }

    public double getAvailablePositionRate() {
        return ((double) capacity - (double) parkingTicketCarMap.size()) / (double) capacity;
    }
}
