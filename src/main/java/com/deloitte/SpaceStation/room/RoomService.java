package com.deloitte.SpaceStation.room;

import com.deloitte.SpaceStation.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

//    public List<Room> getRoomsByAvailability(LocalDate dateStart, LocalDate dateEnd) {
//        return roomRepository.getRoomsByAvailability(dateStart, dateEnd);
//    }
}
