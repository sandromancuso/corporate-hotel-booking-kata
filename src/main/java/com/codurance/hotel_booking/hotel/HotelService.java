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
        if (hotelRepository.findById(hotelId).isEmpty()) {
            throw new HotelDoesNotExistException();
        }
        Room room = new Room(hotelId, roomNumber, roomType);
        if (hotelRepository.findRoom(hotelId, roomNumber).isEmpty()) {
            hotelRepository.add(room);
        } else {
            hotelRepository.update(room);
        }
    }
}
