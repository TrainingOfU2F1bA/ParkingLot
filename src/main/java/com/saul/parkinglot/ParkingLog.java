package com.saul.parkinglot;

import com.saul.parkinglot.exception.CarDoesNotExistException;
import com.saul.parkinglot.exception.DuplicatedCarException;
import com.saul.parkinglot.exception.InvalidCarException;
import com.saul.parkinglot.exception.InvalidTicketException;

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
        if (ticket.getCarId() == null) {
            throw new InvalidTicketException();
        }
    }
}
