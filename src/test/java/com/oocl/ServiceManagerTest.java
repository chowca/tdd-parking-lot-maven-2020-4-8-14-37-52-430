package com.oocl;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceManagerTest {
    private ServiceManager serviceManager;
    private ParkingBoy parkingBoy;
    private SmartParkingBoy smartParkingBoy;
    private SuperSmartParkingBoy superSmartParkingBoy;

    @Before
    public void setUp() {
        parkingBoy = new ParkingBoy(new ParkingLot(1));
        smartParkingBoy = new SmartParkingBoy(new ParkingLot(2));
        superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(3));
        serviceManager = new ServiceManager();
    }

    @Test
    public void should_assign_different_parking_boys_in_management_list() {
        serviceManager.assignParkingBoyInList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
        assertEquals(serviceManager.getParkingBoys().get(0), parkingBoy);
        assertEquals(serviceManager.getParkingBoys().get(1), smartParkingBoy);
        assertEquals(serviceManager.getParkingBoys().get(2), superSmartParkingBoy);
    }

    @Test
    public void should_park_cars_by_different_parking_boys() {
        List<Car> cars = new ArrayList<>();
        List<ParkingTicket> parkingTickets = new ArrayList<>();
        serviceManager.assignParkingBoyInList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
        for (int index = 0; index < 6; index++) {
            cars.add(new Car());
        }
        for (int index = 0; index < cars.size(); index++) {
            parkingTickets.add(index, serviceManager.parkByParkingBoy(cars.get(index)));
        }
        assertEquals(cars.get(0), parkingBoy.fetch(parkingTickets.get(0)));
        assertEquals(cars.get(1), smartParkingBoy.fetch(parkingTickets.get(1)));
        assertEquals(cars.get(2), smartParkingBoy.fetch(parkingTickets.get(2)));
        assertEquals(cars.get(3), superSmartParkingBoy.fetch(parkingTickets.get(3)));
        assertEquals(cars.get(4), superSmartParkingBoy.fetch(parkingTickets.get(4)));
        assertEquals(cars.get(5), superSmartParkingBoy.fetch(parkingTickets.get(5)));
    }

    @Test
    public void should_fetch_cars_with_parking_tickets_from_any_parking_boy_in_list() {
        List<Car> cars = new ArrayList<>();
        List<Car> fetchedCars = new ArrayList<>();
        List<ParkingTicket> parkingTickets = new ArrayList<>();
        serviceManager.assignParkingBoyInList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
        for (int index = 0; index < 6; index++) {
            cars.add(new Car());
        }
        for (int index = 0; index < cars.size(); index++) {
            parkingTickets.add(index, serviceManager.parkByParkingBoy(cars.get(index)));
            fetchedCars.add(index, serviceManager.fetchByParkingTicket(parkingTickets.get(index)));
        }
        assertEquals(cars.get(0),fetchedCars.get(0));
        assertEquals(cars.get(1),fetchedCars.get(1));
        assertEquals(cars.get(2),fetchedCars.get(2));
        assertEquals(cars.get(3),fetchedCars.get(3));
        assertEquals(cars.get(4),fetchedCars.get(4));
        assertEquals(cars.get(5),fetchedCars.get(5));
    }
}