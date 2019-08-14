package com.codurance.hotel_booking.hotel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class HotelRepository {

    private List<Hotel> hotels = new ArrayList<>();
    private List<Room> rooms = new LinkedList<>();

    void add(Hotel hotel) {
        hotels.add(hotel);
    }

    Optional<Hotel> findHotelById(int hotelId) {
        return hotels.stream()
                        .filter(h -> h.id == hotelId)
                        .findFirst();
    }

    void add(Room room) {
        rooms.add(room);
    }

    Optional<Room> findRoom(int hotelId, int roomNumber) {
        return rooms.stream()
                        .filter(r -> r.hotelId == hotelId
                                    && r.number == roomNumber)
                        .findFirst();
    }

    void update(Room room) {
        findRoom(room.hotelId, room.number)
                .ifPresent(r -> rooms.remove(r));
        add(room);
    }

}
