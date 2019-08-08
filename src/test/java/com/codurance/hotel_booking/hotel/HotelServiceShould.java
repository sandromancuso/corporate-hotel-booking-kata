package com.codurance.hotel_booking.hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codurance.hotel_booking.hotel.RoomType.MASTER_SUITE;
import static java.util.Optional.empty;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HotelServiceShould {

    private static final int HOTEL_ID = 1;
    private static final Hotel HOTEL = new Hotel(HOTEL_ID);

    private HotelRepository hotelRepository;
    private HotelService hotelService;

    @BeforeEach
    public void initialise() {
        hotelRepository = mock(HotelRepository.class);
        hotelService = new HotelService(hotelRepository);
    }

    @Test
    public void
    create_a_hotel_when_one_does_not_exist() {
        given(hotelRepository.findHotelBy(HOTEL_ID)).willReturn(empty());

        hotelService.setRoomType(HOTEL_ID, MASTER_SUITE, 1);

        verify(hotelRepository).createHotel(HOTEL);
    }

}
