package com.saul.parkinglot;

import java.util.Arrays;

public class GraduateParkingBoy {
    private ParkingLog[] parkingLogs;

    public void manage(ParkingLog... parkingLogs) {
        this.parkingLogs = parkingLogs;
    }

    public Ticket park(Car car) {
       return Arrays.stream(parkingLogs).findFirst().get().park(car);
    }
}
