package com.codurance.hotel_booking.hotel;

public class HotelService {

    private HotelRepository hotelRepository;

    public HotelService() {
        hotelRepository = new HotelRepository();
    }

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void addHotel(int id, String name) {
        if (hotelRepository.findById(id).isPresent()) {
            throw new HotelExistException();
        }
        hotelRepository.add(new Hotel(id, name));
    }

    public void setRoom(int hotelId, int roomNumber, RoomType roomType) {
        throw new HotelDoesNotExistException();
    }
}
