package com.codurance.hotel_booking;

import com.codurance.hotel_booking.company.CompanyService;
import com.codurance.hotel_booking.employee.Booking;
import com.codurance.hotel_booking.employee.BookingService;
import com.codurance.hotel_booking.hotel.HotelService;
import com.codurance.hotel_booking.hotel.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static com.codurance.hotel_booking.hotel.RoomType.MASTER_SUITE;
import static org.assertj.core.api.Assertions.assertThat;

public class SimpleBookingFeature {

    private static final int HOTEL_ID = 1;
    private static final RoomType ROOM_TYPE = MASTER_SUITE;
    private static final int QUANTITY = 3;
    private static final int COMPANY_ID = 1000;
    private static final int EMPLOYEE_ID = 34034;
    private static final LocalDate CHECKIN = LocalDate.of(2019, 8, 10);
    private static final LocalDate CHECKOUT = LocalDate.of(2019, 8, 17);
    private static final String HOTEL_NAME = "Hilton";
    private static final int ROOM_NUMBER = 13;
    private HotelService hotelService;
    private CompanyService companyService;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        hotelService = new HotelService();
        companyService = new CompanyService();
        bookingService = new BookingService();
    }

    @Test public void
    employee_can_book_a_room() {
        hotelService.addHotel(HOTEL_ID, HOTEL_NAME);
        hotelService.addRoom(HOTEL_ID, ROOM_NUMBER, MASTER_SUITE);
        companyService.addEmployee(COMPANY_ID, EMPLOYEE_ID);

        Optional<Booking> booking = bookingService.book(EMPLOYEE_ID, HOTEL_ID, ROOM_TYPE, CHECKIN, CHECKOUT);

        assertThat(booking).isPresent();
    }

}
