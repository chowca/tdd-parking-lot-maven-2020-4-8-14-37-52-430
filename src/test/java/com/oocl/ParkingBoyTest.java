package com.oocl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ParkingBoyTest {
    ParkingBoy parkingBoy;
    Car car;
    ParkingTicket parkingTicket;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp(){
        parkingBoy = new ParkingBoy(new ParkingLot());
    }
    @Test
    public void should_not_return_car_but_error_when_no_ticket_provided(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.NO_PARKING_TICKET);
        car = parkingBoy.fetch(parkingTicket);
        assertNull(car);
    }

    @Test
    public void should_not_return_car_but_error_when_used_ticket_provided(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.UNRECOGNIZED_PARKING_TICKET);
        parkingTicket = parkingBoy.park(new Car());
        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        car = parkingBoy.fetch(parkingTicket);
        assertNotEquals(fetchedCar,car);
        assertNull(car);
    }
}