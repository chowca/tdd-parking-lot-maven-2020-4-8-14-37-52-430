package com.oocl;

import org.junit.Test;

import static org.junit.Assert.*;

public class SmartParkingBoyTest {
    private SmartParkingBoy smartParkingBoy;
    private ParkingTicket parkingTicket;

    @Test
    public void should_park_car_into_2nd_parking_lot_when_there_is_less_empty_position_the_1st_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot();
        smartParkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        parkingTicket = smartParkingBoy.park(new Car());
        ParkingLot parkingLot = parkingTicket.getParkingLot();
        assertEquals(secondParkingLot, parkingLot);
    }

}