package com.saul.parkinglot;

import com.saul.parkinglot.exception.NoEnoughCarSiteExpection;

import java.util.function.BinaryOperator;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparingInt;

public class SmartParkingBoy extends GraduateParkingBoy {
    @Override
    public Ticket park(Car car) {
        stream(parkingLogs).forEach(parkingLog -> parkingLog.checkCar(car));
        return stream(parkingLogs)
                .filter(parkingLog -> !parkingLog.isFilled())
                .sorted(comparingInt(ParkingLog::countRemainSite))
                .reduce(getFirstParkingLotWithAsMoreRemainAsPossible()
                )
                .orElseThrow(NoEnoughCarSiteExpection::new)
                .park(car);

    }

    protected BinaryOperator<ParkingLog> getFirstParkingLotWithAsMoreRemainAsPossible() {
        return (parkingLog,parkingLogWithMoreRemain) ->
            parkingLogWithMoreRemain.countRemainSite() ==
                    parkingLog.countRemainSite() ?
                    parkingLog : parkingLogWithMoreRemain;
    }
}
