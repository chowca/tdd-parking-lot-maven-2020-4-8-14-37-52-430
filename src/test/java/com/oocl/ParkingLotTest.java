package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
    private ParkingLot parkingLot;
    @Before
    public void setUp() throws Exception{
        //before method
        parkingLot = new ParkingLot();
    }

    @Test
    public void should_return_parking_ticket_when_parking_boy_park_car(){
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.park(car);

        Assert.assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_boy_fetch_car_with_parking_ticket(){
        Car car = new Car();

        ParkingTicket parkingTicket = parkingLot.park(car);
        Car fetchedCar = parkingLot.fetch(parkingTicket);

       Assert.assertEquals(car, fetchedCar);
    }
}
