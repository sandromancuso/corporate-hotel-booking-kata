package com.codurance.hotel_booking.hotel;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

class Hotel {

    public final int id;

    public Hotel(int id, String hotelName) {
        this.id = id;
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
