package com.saul.parkinglot;

import com.saul.parkinglot.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SmartParkingBoyTest extends GraduateParkingBoyTest{
    @Override
    protected GraduateParkingBoy getParkingBoy() {
        return new SmartParkingBoy();
    }

    @Test
    void should_return_a_ticket_with_car_id_and_car_be_parked_on_parkingLotA_of_parked_car_when_park_given_one_car_with_a_carId_which_has_not_been_exist_in_parking_lot_and_parking_log_a_and_b_both_have_same_remaining_car_sites() {
        super.should_return_a_ticket_with_car_id_and_car_be_parked_on_parkingLotA_of_parked_car_when_park_given_one_car_with_a_carId_which_has_not_been_exist_in_parking_lot_and_parking_log_a_and_b_both_have_enough_car_sites();
    }

    @Test
    void should_return_a_ticket_with_car_id_and_car_be_parked_on_parkingLotA_of_parked_car_when_park_given_one_car_with_a_carId_which_has_not_been_exist_in_parking_lot_and_parking_log_a_and_b_both_have_remaining_car_sites_but_b_enough_than_a() {
        GraduateParkingBoy parkingBoy = getParkingBoy();
        ParkingLog parkingLogA = spy(new ParkingLog(1));
        ParkingLog parkingLogB = spy(new ParkingLog(2));
        parkingBoy.manage(parkingLogA, parkingLogB);
        Car car = new Car("123");
        Ticket ticket = parkingBoy.park(car);
        verify(parkingLogA, times(0)).park(car);
        verify(parkingLogB, times(1)).park(car);
        assertEquals(ticket.getCarId(), car.getCarId());
    }


}
