package com.saul.parkinglot;

import com.saul.parkinglot.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class GraduateParkingBoyTest {

    @Test
    void should_return_a_ticket_with_car_id_and_car_be_parked_on_parkingLotA_of_parked_car_when_park_given_one_car_with_a_carId_which_has_not_been_exist_in_parking_lot_and_parking_log_a_and_b_both_have_enough_car_sites() {
        GraduateParkingBoy parkingBoy = getParkingBoy();
        ParkingLog parkingLogA = spy(new ParkingLog(1));
        ParkingLog parkingLogB = spy(new ParkingLog(1));
        parkingBoy.manage(parkingLogA, parkingLogB);
        Car car = new Car("123");
        Ticket ticket = parkingBoy.park(car);
        verify(parkingLogA, times(1)).park(car);
        verify(parkingLogB, times(0)).park(car);
        assertEquals(ticket.getCarId(), car.getCarId());
    }

    protected GraduateParkingBoy getParkingBoy() {
        return new GraduateParkingBoy();
    }

    @Test
    void should_return_a_ticket_with_car_id_and_car_be_parked_on_parkingLotA_of_parked_car_when_park_given_one_car_with_a_carId_which_has_not_been_exist_in_parking_lot_and_parking_log_and_only_b_have_enough_car_sites() {
        GraduateParkingBoy parkingBoy = getParkingBoy();
        ParkingLog parkingLogA = spy(new ParkingLog(0));
        ParkingLog parkingLogB = spy(new ParkingLog(1));
        parkingBoy.manage(parkingLogA, parkingLogB);
        Car car = new Car("123");
        Ticket ticket = parkingBoy.park(car);
        verify(parkingLogA, times(0)).park(car);
        verify(parkingLogB, times(1)).park(car);
        assertEquals(ticket.getCarId(), car.getCarId());
    }

    @Test
    void should_return_a_ticket_with_car_id_and_car_be_parked_on_parkingLotA_of_parked_car_when_park_given_one_car_with_a_carId_which_has_not_been_exist_in_parking_lot_and_parking_log_a_and_b_both_have_not_enough_car_sites() {
        GraduateParkingBoy parkingBoy = getParkingBoy();
        ParkingLog parkingLogA = new ParkingLog(0);
        ParkingLog parkingLogB = new ParkingLog(0);
        parkingBoy.manage(parkingLogA, parkingLogB);
        Car car = new Car("123");
        assertThrows(NoEnoughCarSiteExpection.class, () -> parkingBoy.park(car));
    }

    @Test
    void should_throw_duplicated_car_exception_when_park_given_one_car_with_a_carId_which_has_been_exist_in_parking_lot_and_either_parking_log_a_and_b_have_enough_car_sites() {
        GraduateParkingBoy parkingBoy = getParkingBoy();
        ParkingLog parkingLogA = new ParkingLog(0);
        ParkingLog parkingLogB = new ParkingLog(1);
        parkingBoy.manage(parkingLogA, parkingLogB);
        Car car = new Car("123");
        parkingBoy.park(car);
        assertThrows(DuplicatedCarException.class, () -> parkingBoy.park(car));
    }

    @Test
    void should_throw_invalid_car_exception_when_park_given_one_car_without_carId_and_parking_log_a_and_b_both_have_enough_car_sites() {
        GraduateParkingBoy parkingBoy = getParkingBoy();
        ParkingLog parkingLogA = new ParkingLog(1);
        ParkingLog parkingLogB = new ParkingLog(1);
        parkingBoy.manage(parkingLogA, parkingLogB);
        Car car = new Car(null);
        assertThrows(InvalidCarException.class, () -> parkingBoy.park(new Car(null)));
    }

    @Test
    void should_throwthrow_car_does_not_exist_exception_when_park_given_no_car_and_parking_log_a_and_b_both_have_enough_car_sites() {
        GraduateParkingBoy parkingBoy = getParkingBoy();
        ParkingLog parkingLogA = new ParkingLog(1);
        ParkingLog parkingLogB = new ParkingLog(1);
        parkingBoy.manage(parkingLogA, parkingLogB);
        assertThrows(CarDoesNotExistException.class, () -> parkingBoy.park(null));
    }

    @Test
    void should_fetch_success_when_fetch_given_one_ticket_with_a_carId_of_a_car_which_exist_in_parking_log() {
        GraduateParkingBoy parkingBoy = getParkingBoy();
        ParkingLog parkingLogA = spy(new ParkingLog(1));
        ParkingLog parkingLogB = new ParkingLog(1);
        parkingBoy.manage(parkingLogA, parkingLogB);
        Ticket ticket = parkingBoy.park(new Car("123"));
        assertDoesNotThrow(() -> parkingBoy.fetch(ticket));
        verify(parkingLogA,times(1)).fetch(ticket);
    }

    @Test
    void should_throw_invalid_ticket_exception_when_fetch_given_one_ticket_without_a_carId() {
        GraduateParkingBoy parkingBoy = getParkingBoy();
        ParkingLog parkingLogA = new ParkingLog(1);
        ParkingLog parkingLogB = new ParkingLog(1);
        parkingBoy.manage(parkingLogA, parkingLogB);
        assertThrows(InvalidTicketException.class, () -> parkingBoy.fetch(new Ticket(null)));
    }

    @Test
    void should_throw_ticket_does_not_exist_exception_when_fetch_given_no_ticket() {
        ParkingLog parkingLog = new ParkingLog(1);
        assertThrows(TicketDoesNotExistException.class, () -> parkingLog.fetch(null));
    }

    @Test
    void should_throw_invalid_ticket_exception_when_fetch_given_one_ticket_with_a_carId_of_a_car_which_does_not_exist_in_parking_log() {
        ParkingLog parkingLog = new ParkingLog(1);
        assertThrows(InvalidTicketException.class, () -> parkingLog.fetch(new Ticket("123")));
    }

    @Test
    void should_throw_no_enough_car_site_exception_when_park_given_one_car_with_a_carId_which_has_not_been_exist_in_parking_lot_but_car_sites_is_not_enough() {
        ParkingLog parkingLog = new ParkingLog(1);
        Car car = new Car("123");
        parkingLog.park(car);
        assertThrows(NoEnoughCarSiteExpection.class, () -> parkingLog.park(new Car("456")));
    }
}
