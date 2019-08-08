package com.codurance.hotel_booking.hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.codurance.hotel_booking.hotel.RoomType.MASTER_SUITE;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class HotelServiceShould {

    private static final int HOTEL_ID = 1;
    private static final String HOTEL_NAME = "Marriott";
    private static final Hotel HOTEL = new Hotel(HOTEL_ID, HOTEL_NAME);
    private static final int ROOM_NUMBER = 103;

    private HotelRepository hotelRepository;
    private HotelService hotelService;

    @BeforeEach
    public void initialise() {
        hotelRepository = mock(HotelRepository.class);
        hotelService = new HotelService(hotelRepository);
    }

    @Test public void
    add_a_new_hotel() {
        Hotel hotel = new Hotel(HOTEL_ID, HOTEL_NAME);

        hotelService.addHotel(HOTEL_ID, HOTEL_NAME);

        verify(hotelRepository).add(hotel);
    }

    @Test public void
    throws_exception_when_adding_an_existing_hotel() {
        given(hotelRepository.findById(HOTEL_ID)).willReturn(Optional.of(HOTEL));

        assertThrows(HotelExistException.class,
                () -> hotelService.addHotel(HOTEL_ID, HOTEL_NAME));

        verify(hotelRepository, never()).add(HOTEL);
    }

    @Test public void
    throws_exception_when_adding_a_room_to_a_non_existing_hotel() {
        given(hotelRepository.findById(HOTEL_ID)).willReturn(empty());

        assertThrows(HotelDoesNotExistException.class,
                () -> hotelService.setRoom(HOTEL_ID, ROOM_NUMBER, MASTER_SUITE));
    }
    
}
