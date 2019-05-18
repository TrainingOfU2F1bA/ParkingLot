package com.saul.parkinglot;

import com.saul.parkinglot.exception.*;

import java.util.HashMap;
import java.util.Optional;

public class ParkingLog {

    private HashMap<String, Car> carSites = new HashMap<>();
    private int capacity;

    public ParkingLog(int capacity) {
        this.capacity = capacity;
    }

    public Ticket park(Car car) {
        checkCar(car);
        if (isFilled()) {
            throw new NoEnoughCarSiteExpection();
        }
        carSites.put(car.getCarId(), car);
        return new Ticket(car.getCarId());
    }

    public void fetch(Ticket ticket) {
        checkTicket(ticket);
        Optional.ofNullable(carSites.remove(ticket.getCarId()))
                .orElseThrow(InvalidTicketException::new);
    }

    public boolean isFilled() {
        return carSites.size() >= capacity;
    }

    public void checkCar(Car car) {
        if (car == null) {
            throw new CarDoesNotExistException();
        }
        if (car.getCarId() == null) {
            throw new InvalidCarException();
        }
        if (contains(car.getCarId())){
            throw new DuplicatedCarException();
        }
    }

    public void checkTicket(Ticket ticket) {
        if (ticket == null) {
            throw new TicketDoesNotExistException();
        }
        if (ticket.getCarId() == null) {
            throw new InvalidTicketException();
        }
    }

    public boolean contains(String carId) {
        return carSites.containsKey(carId);
    }


    public int countRemainSite() {
        return capacity - carSites.size();
    }

    public double computeRateOfRemain() {
        return (capacity - carSites.size()) * 1d / capacity;
    }
}
