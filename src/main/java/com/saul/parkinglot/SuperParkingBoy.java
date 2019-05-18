package com.saul.parkinglot;

import com.saul.parkinglot.exception.NoEnoughCarSiteExpection;

import java.util.function.BinaryOperator;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparingDouble;

public class SuperParkingBoy extends SmartParkingBoy {
    @Override
    public Ticket park(Car car) {
        stream(parkingLogs).forEach(parkingLog -> parkingLog.checkCar(car));
        return stream(parkingLogs)
                .filter(parkingLog -> !parkingLog.isFilled())
                .sorted(comparingDouble(ParkingLog::computeRateOfRemain))
                .reduce(getFirstParkingLotWithAsBiggerRemainRateAsPossible())
                .orElseThrow(NoEnoughCarSiteExpection::new)
                .park(car);
    }

    private BinaryOperator<ParkingLog> getFirstParkingLotWithAsBiggerRemainRateAsPossible() {
        return (parkingLog, parkingLogWithBiggerRemainRate) -> {
            if (parkingLog.computeRateOfRemain() == parkingLogWithBiggerRemainRate.computeRateOfRemain()) {
                return parkingLogWithBiggerRemainRate.countRemainSite() == parkingLog.countRemainSite()
                        ? parkingLog : parkingLogWithBiggerRemainRate;
            }
            return parkingLogWithBiggerRemainRate;
        };
    }
}
