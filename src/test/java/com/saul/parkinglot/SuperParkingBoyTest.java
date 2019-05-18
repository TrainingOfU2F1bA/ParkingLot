package com.saul.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SuperParkingBoyTest {

    @Test
    void should_return_a_ticket_with_car_id_and_car_be_parked_on_parkingLotA_of_parked_car_when_park_given_one_car_with_a_carId_which_has_not_been_exist_in_parking_lot_and_parking_rate_of_a_more_than_b_but_remain_count_of_b_more_than_a() {
        GraduateParkingBoy parkingBoy = new SuperParkingBoy();
        ParkingLog parkingLogA = spy(new ParkingLog(10));
        ParkingLog parkingLogB = spy(new ParkingLog(100));
        parkingBoy.manage(parkingLogA, parkingLogB);

        IntStream.range(0,1).forEach(index -> parkingLogA.park(new Car("A:"+index)));
        IntStream.range(0,12).forEach(index -> parkingLogB.park(new Car("B:"+index)));

        Car car = new Car("123");
        parkingBoy.park(car);
        verify(parkingLogA, times(2)).park(any(Car.class));
        verify(parkingLogB, times(12)).park(any(Car.class));
    }

}

class SuperParkingBoyAndGraduateParkingBoyCompatibilityTest extends GraduateParkingBoyTest {

    @Override
    protected GraduateParkingBoy getParkingBoy() {
        return new SuperParkingBoy();
    }

}

class SuperParkingBoyAndSmartParkingBoyCompatibilityTest extends SmartParkingBoyTest {

    @Override
    protected GraduateParkingBoy getParkingBoy() {
        return new SuperParkingBoy();
    }

}
