package com.saul.parkinglot;

import com.saul.parkinglot.exception.CarDoesNotExistException;
import com.saul.parkinglot.exception.DuplicatedCarException;
import com.saul.parkinglot.exception.InvalidCarException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingLotTest {
    @Test
    void should_return_a_ticket_with_car_id_of_parked_car_when_park_given_one_car_with_a_carId_which_has_not_been_exist_in_parking_lot() {
        ParkingLog parkingLog = new ParkingLog();
        Car car = new Car("123");
        Ticket ticket = parkingLog.park(car);
        assertEquals(ticket.getCarId(), car.getCarId());
    }

    @Test
    void should_throw_duplicated_car_exception_when_park_given_one_car_with_a_carId_which_has_been_exist_in_parking_lot() {
        ParkingLog parkingLog = new ParkingLog();
        assertThrows(DuplicatedCarException.class, () -> parkingLog.park(new Car("456")));
    }

    @Test
    void should_throw_invaild_car_exception_when_park_given_one_car_without_a_carId() {
        ParkingLog parkingLog = new ParkingLog();
        assertThrows(InvalidCarException.class, () -> parkingLog.park(new Car(null)));
    }

    @Test
    void should_throw_car_does_not_exist_exception_when_park_given_no_car() {
        ParkingLog parkingLog = new ParkingLog();
        assertThrows(CarDoesNotExistException.class, () -> parkingLog.park(null));
    }

    @Test
    void should_fetch_success_when_fetch_given_one_ticket_with_a_carId_of_a_car_which_exist_in_parking_log() {
        ParkingLog parkingLog = new ParkingLog();
        Ticket ticket = parkingLog.park(new Car("123"));
        assertDoesNotThrow(() -> parkingLog.fetch(ticket));
    }
}
