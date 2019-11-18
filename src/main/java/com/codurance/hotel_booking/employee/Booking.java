package com.codurance.hotel_booking.employee;

import com.codurance.hotel_booking.hotel.RoomType;

import java.time.LocalDate;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Booking {
    private final String bookingId;
    private final int employeeId;
    private final int hotelId;
    private final RoomType roomType;
    private final LocalDate checkin;
    private final LocalDate checkout;

    public Booking(String bookingId, int employeeId, int hotelId, RoomType roomType, LocalDate checkin, LocalDate checkout) {
        this.bookingId = bookingId;
        this.employeeId = employeeId;
        this.hotelId = hotelId;
        this.roomType = roomType;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
