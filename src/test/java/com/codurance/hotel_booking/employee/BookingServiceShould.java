package com.codurance.hotel_booking.employee;

import com.codurance.hotel_booking.hotel.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class BookingServiceShould {

    private static final String BOOKING_ID = "234SLK";
    private static final int EMPLOYEE_ID = 1231;
    private static final int HOTEL_ID = 2342;
    private static final RoomType ROOM_TYPE = RoomType.STANDARD;
    private static final LocalDate CHECK_IN = LocalDate.now();
    private static final LocalDate CHECK_OUT = CHECK_IN.plusDays(2);
    private static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    private static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);

// OK: Booking should contain a unique ID, employeeId, hotelId, roomType, checkIn and checkOut);
// OK: Check out date must be at least one day after the check in date.
// TODO: Validate if the hotel exists and room type is provided by the hotel
// TODO: Verify if booking is allowed according to the booking policies defined, if any. See Booking Policy Service for more details.
// TODO: Booking should only be allowed if there is at least one room type available during the whole booking period.
// TODO: Keep track of all bookings. E.g. If hotel has 5 standard rooms, we should have no more than 5 bookings in the same day.
// TODO: Hotel rooms can be booked many times as long as there are no conflicts with the dates.
// TODO: Return booking confirmation to the employee or error otherwise (exceptions can also be used).

    private BookingIdGenerator bookingIdGenerator;

    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingIdGenerator = mock(BookingIdGenerator.class);
        bookingService = new BookingService(bookingIdGenerator);
        given(bookingIdGenerator.nextId()).willReturn(BOOKING_ID);
    }

    @Test public void
    return_booking_with_a_unique_id_employee_id_hotel_id_room_type_check_in_and_check_out() {
        Booking expectedBooking = new Booking(BOOKING_ID, EMPLOYEE_ID, HOTEL_ID, ROOM_TYPE, CHECK_IN, CHECK_OUT);

        Optional<Booking> booking = bookingService.book(EMPLOYEE_ID, HOTEL_ID, ROOM_TYPE, CHECK_IN, CHECK_OUT);

        assertThat(booking).contains(expectedBooking);
    }

    @Test public void
    validate_check_out_date_is_at_least_one_day_after_check_in_date() {
        assertThatExceptionOfType(InvalidCheckOutDateException.class)
            .isThrownBy(() -> bookingService.book(EMPLOYEE_ID, HOTEL_ID, ROOM_TYPE, TOMORROW, YESTERDAY));
    }



}