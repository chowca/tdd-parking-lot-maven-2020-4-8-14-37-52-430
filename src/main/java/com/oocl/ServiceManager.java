package com.oocl;

import com.constant.ErrorMsg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceManager implements StandardParkingBoy {
    private List<ParkingBoy> parkingBoys = new ArrayList<>();
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingBoy selectedParkingBoy;
    private ParkingLot selectedParkingLot;

    public ServiceManager(ParkingLot... parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
        this.selectedParkingLot = this.parkingLots.stream().findFirst().orElse(null);
    }

    public List<ParkingLot> getParkingLots() {
        List<ParkingLot> returnParkingLots = new ArrayList<>();
        returnParkingLots = parkingLots;
        return returnParkingLots;
    }

    public void assignParkingBoyInList(ParkingBoy... parkingBoys) {
        this.parkingBoys.addAll(Arrays.asList(parkingBoys));
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }

    public ParkingTicket parkByParkingBoy(Car car) {
        selectedParkingBoy = parkingBoys
                .stream()
                .filter(parkingBoy -> parkingBoy.getSelectedParkingLot() != null)
                .findFirst()
                .orElse(null);
        if (selectedParkingBoy == null) {
            throw new IllegalArgumentException(ErrorMsg.NOT_ENOUGH_POSITION);
        } else {
            return selectedParkingBoy.park(car);
        }
    }

    public Car fetchByParkingTicket(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException(ErrorMsg.NO_PARKING_TICKET);
        } else {
            selectedParkingBoy = parkingBoys
                    .stream()
                    .filter(parkingBoy -> parkingBoy.getParkingLots()
                            .stream()
                            .filter(parkingLot -> parkingLot.isTicketFound(parkingTicket))
                            .findFirst().orElse(null) != null)
                    .findFirst()
                    .orElse(null);
        }
        if (selectedParkingBoy == null) {
            throw new IllegalArgumentException(ErrorMsg.UNRECOGNIZED_PARKING_TICKET);
        } else {
            return selectedParkingBoy.fetch(parkingTicket);
        }
    }

    public void addParkingLot(ParkingLot... parkingLots) {
        this.parkingLots.addAll(Arrays.asList(parkingLots));
    }

    public void removeParkingLot(ParkingLot... parkingLots) {
        this.parkingLots.removeAll(Arrays.asList(parkingLots));
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new IllegalArgumentException(ErrorMsg.NO_PARKING_TICKET);
        } else {
            this.selectedParkingLot = parkingTicket.getParkingLot();
        }
        if ((parkingTicket != null) && (!this.selectedParkingLot.isTicketFound(parkingTicket))) {
            throw new IllegalArgumentException(ErrorMsg.UNRECOGNIZED_PARKING_TICKET);
        } else {
            return this.selectedParkingLot.fetch(parkingTicket);
        }
    }

    public ParkingTicket park(Car car) {
        this.selectedParkingLot = getSelectedParkingLot();
        if (this.selectedParkingLot == null) {
            throw new IllegalArgumentException(ErrorMsg.NOT_ENOUGH_POSITION);
        } else {
            return this.selectedParkingLot.park(car);
        }
    }

    public ParkingLot getSelectedParkingLot() {
        return this.parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().orElse(null);
    }
}
