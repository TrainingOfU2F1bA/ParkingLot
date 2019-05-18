package com.saul.parkinglot;

public class Ticket {
    private String carId;

    public Ticket(String carId) {
        this.carId = carId;
    }

    public String getCarId() {
        return this.carId;
    }
}
