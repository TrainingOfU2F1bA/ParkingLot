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
        if (car == null) {
            throw new CarDoesNotExistException();
        }
        if (car.getCarId() == null) {
            throw new InvalidCarException();
        }
        if (carSites.containsKey(car.getCarId())) throw new DuplicatedCarException();
        if (isFilled()) {
            throw new NoEnoughCarSiteExpection();
        }
        carSites.put(car.getCarId(), car);
        return new Ticket(car.getCarId());
    }

    public void fetch(Ticket ticket) {
        if (ticket == null) {
            throw new TicketDoesNotExistException();
        }
        if (ticket.getCarId() == null) {
            throw new InvalidTicketException();
        }
        Optional.ofNullable(carSites.remove(ticket.getCarId()))
                .orElseThrow(InvalidTicketException::new);
    }

    public boolean isFilled() {
        return carSites.size() >= capacity;
    }
}
