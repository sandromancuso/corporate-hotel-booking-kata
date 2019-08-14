package com.codurance.hotel_booking.hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class HotelRepositoryShould {

    private static final int NONEXISTENT_HOTEL_ID = 4323;
    private static final int HOTEL_ID = 123;
    private static final String HOTEL_NAME = "Hilton";
    private static final Hotel HOTEL = new Hotel(HOTEL_ID, HOTEL_NAME);
    private static final int ROOM_NUMBER = 234;
    private static final Room STANDARD_ROOM = new Room(HOTEL_ID, ROOM_NUMBER, RoomType.STANDARD);

    private HotelRepository hotelRepository;

    @BeforeEach
    public void initialise() {
        hotelRepository = new HotelRepository();
    }

    @Test public void
    return_a_hotel_by_its_id() {
        hotelRepository.add(HOTEL);

        Optional<Hotel> result = hotelRepository.findHotelById(HOTEL_ID);

        assertThat(result).contains(HOTEL);
    }
    
    @Test public void
    return_nothing_when_hotel_is_not_found() {
        Optional<Hotel> result = hotelRepository.findHotelById(NONEXISTENT_HOTEL_ID);

        assertThat(result).isEmpty();
    }

    @Test public void
    find_a_hotel_room_by_its_number() {
        hotelRepository.add(STANDARD_ROOM);

        Optional<Room> result = hotelRepository.findRoom(HOTEL_ID, ROOM_NUMBER);

        assertThat(result).contains(STANDARD_ROOM);
    }

    @Test public void
    update_a_hotel_room() {
        Room room = new Room(HOTEL_ID, ROOM_NUMBER, RoomType.STANDARD);
        Room updatedRoom = new Room(HOTEL_ID, ROOM_NUMBER, RoomType.QUEEN_SUITE);

        hotelRepository.add(room);
        hotelRepository.update(updatedRoom);

        Optional<Room> result = hotelRepository.findRoom(HOTEL_ID, ROOM_NUMBER);

        assertThat(result).contains(updatedRoom);
    }
}