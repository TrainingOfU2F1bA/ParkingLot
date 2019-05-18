package com.saul.parkinglot;

import com.saul.parkinglot.exception.*;

public class ParkingLog {
    public Ticket park(Car car) {
        if (car == null) {
            throw new CarDoesNotExistException();
        }
        if (car.getCarId() == null) {
            throw new InvalidCarException();
        }
        if ("456".equals(car.getCarId())) throw new DuplicatedCarException();
        return new Ticket(car.getCarId());
    }

    public void fetch(Ticket ticket) {
        if (ticket == null) {
            throw new TicketDoesNotExistException();
        }
        if (ticket.getCarId() == null) {
            throw new InvalidTicketException();
        }
    }
}
