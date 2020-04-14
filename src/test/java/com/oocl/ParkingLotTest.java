package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    private ParkingLot parkingLot;

    @Before
    public void setUp() throws Exception {
        //before method
        parkingLot = new ParkingLot();
    }

    @Test
    public void should_return_parking_ticket_when_parking_lot_park_car() {
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.park(car);

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch_car_with_parking_ticket() {
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.park(car);
        Car fetchedCar = parkingLot.fetch(parkingTicket);

        Assert.assertEquals(car, fetchedCar);
    }

    @Test
    public void should_not_return_car_when_parking_boy_fetch_car_with_incorrect_parking_ticket() {
        Car car = new Car();
        parkingLot.park(car);
        Car fetchedCar = parkingLot.fetch(new ParkingTicket(parkingLot));

        Assert.assertNull(fetchedCar);
    }

    @Test
    public void should_not_return_car_with_used_parking_ticket() {
        ParkingTicket parkingTicket = parkingLot.park(new Car());
        parkingLot.fetch(parkingTicket);
        Car car = parkingLot.fetch(parkingTicket);

        Assert.assertNull(car);
    }

    @Test
    public void should_not_park_car_if_the_park_is_full() {
        ParkingLot parkingLotWith1Capacity = new ParkingLot(1);
        parkingLotWith1Capacity.park(new Car());
        ParkingTicket parkingTicket = parkingLotWith1Capacity.park(new Car());

        Assert.assertNull(parkingTicket);
    }

    @Test
    public void should_not_return_car_with_no_parking_ticket() {
        Car car = parkingLot.fetch(null);
        Assert.assertNull(car);
    }

    @Test
    public void should_not_return_parking_ticket_with_parked_car() {
        Car car = new Car();
        parkingLot.park(car);
        ParkingTicket parkingTicket = parkingLot.park(car);
        Assert.assertNull(parkingTicket);
    }
}
