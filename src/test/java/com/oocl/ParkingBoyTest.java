package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingBoyTest {
    private ParkingBoy parkingBoy;
    @Before
    public void setUp() throws Exception{
        //before method
        parkingBoy = new ParkingBoy();
    }

    @Test
    public void should_return_parking_ticket_when_parking_boy_park_car(){
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Assert.assertNotNull(parkingTicket);
    }
}
