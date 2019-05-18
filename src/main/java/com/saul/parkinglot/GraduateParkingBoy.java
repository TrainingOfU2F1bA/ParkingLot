package com.saul.parkinglot;

import static java.util.Arrays.stream;

public class GraduateParkingBoy {
    private ParkingLog[] parkingLogs;

    public void manage(ParkingLog... parkingLogs) {
        this.parkingLogs = parkingLogs;
    }

    public Ticket park(Car car) {
       return stream(parkingLogs)
               .filter(parkingLog -> !parkingLog.isFilled())
               .findFirst()
               .get()
               .park(car);
    }
}
