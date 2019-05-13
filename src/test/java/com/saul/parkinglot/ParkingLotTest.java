package com.saul.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParkingLotTest {
    @Test
    void should_return_a_ticket_with_car_id_of_parked_car_when_park_given_one_car_with_a_carId_which_has_not_been_exist_in_parking_lot() {
        ParkingLog parkingLog = new ParkingLog();
        Car car = new Car("123");
        Ticket ticket = parkingLog.park(car);
        assertEquals(ticket.getCarId(), car.getCarId());
    }
}
