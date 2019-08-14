package com.codurance.hotel_booking.hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.Optional;

import static com.codurance.hotel_booking.hotel.RoomType.*;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
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
    private static final Room STANDARD_ROOM = new Room(HOTEL_ID, ROOM_NUMBER, STANDARD);
    private static final Room QUEEN_SUITE_ROOM = new Room(HOTEL_ID, ROOM_NUMBER, QUEEN_SUITE);

    @BeforeEach
    public void initialise() {
        hotelRepository = mock(HotelRepository.class);
        hotelService = new HotelService(hotelRepository);
        given(hotelRepository.findHotelById(HOTEL_ID)).willReturn(Optional.of(HOTEL));
    }

    @Test public void
    add_a_new_hotel() {
        given(hotelRepository.findHotelById(HOTEL_ID)).willReturn(Optional.empty());
        Hotel hotel = new Hotel(HOTEL_ID, HOTEL_NAME);

        hotelService.addHotel(HOTEL_ID, HOTEL_NAME);

        verify(hotelRepository).add(hotel);
    }

    @Test public void
    throws_exception_when_adding_an_existing_hotel() {
        given(hotelRepository.findHotelById(HOTEL_ID)).willReturn(Optional.of(HOTEL));

        assertThrows(HotelExistException.class,
                () -> hotelService.addHotel(HOTEL_ID, HOTEL_NAME));

        verify(hotelRepository, never()).add(HOTEL);
    }

    @Test public void
    throws_exception_when_adding_a_room_to_a_non_existing_hotel() {
        given(hotelRepository.findHotelById(HOTEL_ID)).willReturn(empty());

        assertThrows(HotelDoesNotExistException.class,
                () -> hotelService.setRoom(HOTEL_ID, ROOM_NUMBER, MASTER_SUITE));
    }

    @Test public void
    add_a_hotel_room() {
        hotelService.setRoom(HOTEL_ID, ROOM_NUMBER, STANDARD);

        verify(hotelRepository).add(STANDARD_ROOM);
    }
    
    @Test public void
    update_a_hotel_room() {
        given(hotelRepository.findRoom(HOTEL_ID, ROOM_NUMBER))
                .willReturn(empty(), Optional.of(STANDARD_ROOM));

        hotelService.setRoom(HOTEL_ID, ROOM_NUMBER, STANDARD);
        hotelService.setRoom(HOTEL_ID, ROOM_NUMBER, QUEEN_SUITE);

        InOrder inOrder = inOrder(hotelRepository);
        inOrder.verify(hotelRepository).add(STANDARD_ROOM);
        inOrder.verify(hotelRepository).update(QUEEN_SUITE_ROOM);
    }

    @Test public void
    find_hotels_by_ids() {          
        given(hotelRepository.findHotelById(HOTEL_ID)).willReturn(Optional.of(HOTEL));

        Optional<Hotel> result = hotelService.findHotelById(HOTEL_ID);

        assertThat(result).contains(HOTEL);
    }
    
}
