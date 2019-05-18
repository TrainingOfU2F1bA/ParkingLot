package com.saul.parkinglot;

import com.saul.parkinglot.exception.*;

import java.util.HashMap;
import java.util.Optional;

public class ParkingLog {

    HashMap<String, Car> carSites = new HashMap<>();

    public Ticket park(Car car) {
        if (car == null) {
            throw new CarDoesNotExistException();
        }
        if (car.getCarId() == null) {
            throw new InvalidCarException();
        }
        if (carSites.containsKey(car.getCarId())) throw new DuplicatedCarException();
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
}
