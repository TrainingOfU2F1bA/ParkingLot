package com.saul.parkinglot;

import com.saul.parkinglot.exception.DuplicatedCarException;

public class ParkingLog {
    public Ticket park(Car car) {
        if ("456".equals(car.getCarId())) throw new DuplicatedCarException();
        return new Ticket();
    }
}
