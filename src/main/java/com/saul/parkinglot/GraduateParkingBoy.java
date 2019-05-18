package com.saul.parkinglot;

import com.saul.parkinglot.exception.NoEnoughCarSiteExpection;

import static java.util.Arrays.stream;

public class GraduateParkingBoy {
    private ParkingLog[] parkingLogs;

    public void manage(ParkingLog... parkingLogs) {
        this.parkingLogs = parkingLogs;
    }

    public Ticket park(Car car) {
        stream(parkingLogs).forEach(parkingLog -> parkingLog.checkDuplicatedCar(car));
       return stream(parkingLogs)
               .filter(parkingLog -> !parkingLog.isFilled())
               .findFirst()
               .orElseThrow(NoEnoughCarSiteExpection::new)
               .park(car);
    }
}
