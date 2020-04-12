package com.oocl;

import org.junit.Test;

import static org.junit.Assert.*;

public class SuperSmartParkingBoyTest {
    private SuperSmartParkingBoy superSmartParkingBoy;

    @Test
    public void should_park_car_into_the_parking_lot_with_largest_available_position_rate() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot();
        superSmartParkingBoy = new SuperSmartParkingBoy(firstParkingLot, secondParkingLot);
        ParkingTicket parkingTicket1 = superSmartParkingBoy.park(new Car());
        ParkingTicket parkingTicket2 = superSmartParkingBoy.park(new Car());
        ParkingTicket parkingTicket3 = superSmartParkingBoy.park(new Car());
        ParkingLot resultedParkingLot1 = parkingTicket1.getParkingLot();
        ParkingLot resultedParkingLot2 = parkingTicket2.getParkingLot();
        ParkingLot resultedParkingLot3 = parkingTicket3.getParkingLot();
        assertEquals(secondParkingLot, resultedParkingLot1);
        assertEquals(firstParkingLot, resultedParkingLot2);
        assertEquals(secondParkingLot, resultedParkingLot3);
    }
}