package com.deloitte.SpaceStation.room.service;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.room.model.Room;
import com.deloitte.SpaceStation.room.model.RoomRequestDto;
import com.deloitte.SpaceStation.room.repository.RoomRepository;
import com.deloitte.SpaceStation.room.util.RoomMapper;
import com.deloitte.SpaceStation.worksite.service.WorksiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final WorksiteService worksiteService;

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

    @Override
    public Room addRoom(RoomRequestDto roomRequestDto) {
        checkIfRoomNameIsAvailable(roomRequestDto.getName());

        Room room = roomMapper.mapRoomRequestDtoToRoom(roomRequestDto);
        room = roomRepository.save(room);

        // the worksites in the room are added first in the X axis and then in the Y axis
        long y_move = 1L;
        long x_move = 1L;
        for (long worksiteInRoomId = 1; worksiteInRoomId <= roomRequestDto.getNumberOfWorksites(); worksiteInRoomId++) {

            if (x_move > roomRequestDto.getDimensionX()) {
                ++y_move;
                x_move = 1L;
            }
            long coordinateX = x_move;
            long coordinateY = y_move;
            ++x_move;

            worksiteService.addWorksite(worksiteInRoomId, room, coordinateX, coordinateY);
        }
        return room;
    }

    @Transactional
    public void checkIfRoomNameIsAvailable(String name) {
        List<Room> createdRooms = roomRepository.findAllByName(name);
        if (createdRooms.size() != 0) {
            throw new SpaceStationException(Error.ROOM_NAME_IS_NOT_UNIQUE);
        }
    }
}
