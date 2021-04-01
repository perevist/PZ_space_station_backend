package com.deloitte.SpaceStation.room.service;

import com.deloitte.SpaceStation.room.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    List<Room> getRooms();

    List<Room> getRoomsByFloor(int floor);

    List<Room> getRoomsByAvailabilityDate(LocalDate startDate, LocalDate endDate);

    List<Room> getRoomsByFloorAndAvailabilityDate(int floor, LocalDate startDate, LocalDate endDate);
}
