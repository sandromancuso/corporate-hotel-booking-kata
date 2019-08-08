package com.codurance.hotel_booking.hotel;

public class HotelService {

    private HotelRepository hotelRepository;

    public HotelService() {
        hotelRepository = new HotelRepository();
    }

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void setRoomType(int hotelId, RoomType roomType, int quantity) {
        hotelRepository.createHotel(new Hotel(hotelId));
    }
}
