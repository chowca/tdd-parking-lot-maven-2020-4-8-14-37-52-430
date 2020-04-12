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
    public void setUp() {
        parkingBoy = new ParkingBoy(new ParkingLot());
    }

    @Test
    public void should_not_return_car_but_error_when_no_ticket_provided() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.NO_PARKING_TICKET);
        car = parkingBoy.fetch(parkingTicket);
        assertNull(car);
    }

    @Test
    public void should_not_return_car_but_error_when_used_ticket_provided() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.UNRECOGNIZED_PARKING_TICKET);
        parkingTicket = parkingBoy.park(new Car());
        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        car = parkingBoy.fetch(parkingTicket);
        assertNotEquals(fetchedCar, car);
        assertNull(car);
    }

    @Test
    public void should_not_return_parking_ticket_but_error_when_fetching_car_into_full_parking_lot() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.NOT_ENOUGH_POSITION);
        parkingBoy = new ParkingBoy(new ParkingLot(1));
        parkingBoy.park(new Car());
        parkingTicket = parkingBoy.park(new Car());
        assertNull(parkingTicket);
    }

    @Test
    public void should_park_car_into_2nd_parking_lot_when_the_1st_parking_lot_is_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        car = new Car();
        parkingBoy.park(new Car());
        parkingTicket = parkingBoy.park(car);
        Car fetchedCar = secondParkingLot.fetch(parkingTicket);
        assertTrue(firstParkingLot.isFull());
        assertTrue(!secondParkingLot.isFull());
        assertEquals(car, fetchedCar);
    }

    @Test
    public void should_return_car_from_2nd_parking_lot_when_the_1st_parking_lot_is_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        car = new Car();
        parkingBoy.park(new Car());
        parkingTicket = parkingBoy.park(car);
        Car fetchedCar = parkingBoy.fetch(parkingTicket);
        assertTrue(firstParkingLot.isFull());
        assertTrue(!secondParkingLot.isFull());
        assertEquals(car, fetchedCar);
    }
}