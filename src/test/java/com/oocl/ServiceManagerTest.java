package com.oocl;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceManagerTest {
    private ServiceManager serviceManager;
    private ParkingBoy parkingBoy;
    private SmartParkingBoy smartParkingBoy;
    private SuperSmartParkingBoy superSmartParkingBoy;
    private List<Car> cars;
    private List<Car> fetchedCars;
    private List<ParkingTicket> parkingTickets;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        parkingBoy = new ParkingBoy(new ParkingLot(1));
        smartParkingBoy = new SmartParkingBoy(new ParkingLot(2));
        superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(3));
        serviceManager = new ServiceManager();

        cars = new ArrayList<>();
        fetchedCars = new ArrayList<>();
        parkingTickets = new ArrayList<>();
        serviceManager.assignParkingBoyInList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
        for (int index = 0; index < 6; index++) {
            cars.add(new Car());
        }
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
        for (int index = 0; index < cars.size(); index++) {
            parkingTickets.add(index, serviceManager.parkByParkingBoy(cars.get(index)));
            fetchedCars.add(index, serviceManager.fetchByParkingTicket(parkingTickets.get(index)));
        }
        assertEquals(cars.get(0), fetchedCars.get(0));
        assertEquals(cars.get(1), fetchedCars.get(1));
        assertEquals(cars.get(2), fetchedCars.get(2));
        assertEquals(cars.get(3), fetchedCars.get(3));
        assertEquals(cars.get(4), fetchedCars.get(4));
        assertEquals(cars.get(5), fetchedCars.get(5));
    }

    @Test
    public void should_not_return_parking_ticket_but_error_when_all_parking_lots_are_full() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.NOT_ENOUGH_POSITION);
        for (int index = 0; index < cars.size(); index++) {
            parkingTickets.add(index, serviceManager.parkByParkingBoy(cars.get(index)));
        }
        ParkingTicket parkingTicket = serviceManager.parkByParkingBoy(new Car());
        assertNull(parkingTicket);
    }

    @Test
    public void should_not_return_car_but_error_when_no_parking_ticket_provided() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.NO_PARKING_TICKET);
        Car fetchedCar = serviceManager.fetchByParkingTicket(null);
        assertNull(fetchedCar);
    }

    @Test
    public void should_not_return_car_but_error_when_used_parking_ticket_provided() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(ErrorMsg.UNRECOGNIZED_PARKING_TICKET);
        ParkingTicket parkingTicket = serviceManager.parkByParkingBoy(new Car());
        Car fetchedCar = serviceManager.fetchByParkingTicket(parkingTicket);
        Car car = serviceManager.fetchByParkingTicket(parkingTicket);
        assertNotEquals(fetchedCar, car);
        assertNull(car);
    }

    @Test
    public void should_add_a_new_parking_lot_in_management_list(){
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot parkingLot3 = new ParkingLot();
        ServiceManager serviceManager1 = new ServiceManager(parkingLot1,parkingLot2);
        serviceManager1.addParkingLot(parkingLot3);
        List<ParkingLot> parkingLots = serviceManager1.getParkingLots();
        assertEquals(parkingLots.get(0), parkingLot1);
        assertEquals(parkingLots.get(1), parkingLot2);
        assertEquals(parkingLots.get(2), parkingLot3);
        assertEquals(parkingLots.size(),3);
    }

    @Test
    public void should_remove_parking_lot_in_management_list(){
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLot parkingLot3 = new ParkingLot();
        ServiceManager serviceManager1 = new ServiceManager(parkingLot1,parkingLot2, parkingLot3);
        serviceManager1.removeParkingLot(parkingLot1, parkingLot2);
        List<ParkingLot> parkingLots = serviceManager1.getParkingLots();
        assertEquals(parkingLots.get(0), parkingLot3);
        assertEquals(parkingLots.size(),1);
    }
}