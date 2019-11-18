package com.codurance.hotel_booking.employee;

import com.codurance.hotel_booking.hotel.RoomType;

import java.time.LocalDate;
import java.util.Optional;

public class BookingService {

    private BookingIdGenerator bookingIdGenerator = new BookingIdGenerator();

    public BookingService() {
    }

    public BookingService(BookingIdGenerator bookingIdGenerator) {
        this.bookingIdGenerator = bookingIdGenerator;
    }

    public Optional<Booking> book(int employeeId,
                                  int hotelId,
                                  RoomType roomType,
                                  LocalDate checkin,
                                  LocalDate checkout) {
        if (checkout.isBefore(checkin)) {
            throw new InvalidCheckOutDateException();
        }
        return Optional.of(new Booking(bookingIdGenerator.nextId(), employeeId, hotelId, roomType, checkin, checkout));
    }
}
