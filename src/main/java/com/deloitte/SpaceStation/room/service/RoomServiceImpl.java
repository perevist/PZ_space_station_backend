package com.deloitte.SpaceStation.room.service;

import com.deloitte.SpaceStation.room.model.Room;
import com.deloitte.SpaceStation.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getRoomsByFloor(int floor) {
        return roomRepository.getAllByFloor(floor);
    }

    @Override
    public List<Room> getRoomsByAvailabilityDate(LocalDate startDate, LocalDate endDate) {
        return roomRepository.getAllByAvailabilityDate(startDate, endDate);
    }

    @Override
    public List<Room> getRoomsByFloorAndAvailabilityDate(int floor, LocalDate startDate, LocalDate endDate) {
        return roomRepository.getAllByFloorAndAvailabilityDate(floor, startDate, endDate);
    }
}
