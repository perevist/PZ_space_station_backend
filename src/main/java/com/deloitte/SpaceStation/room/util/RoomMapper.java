package com.deloitte.SpaceStation.room.util;

import com.deloitte.SpaceStation.room.model.Room;
import com.deloitte.SpaceStation.room.model.RoomRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomMapper {

    public Room mapRoomRequestDtoToRoom(RoomRequestDto roomRequestDto) {
        Room room = new Room();
        room.setName(roomRequestDto.getName());
        room.setFloor(roomRequestDto.getFloor());
        room.setNumberOfWorksites(roomRequestDto.getNumberOfWorksites());
        room.setDimensionX(roomRequestDto.getDimensionX());
        room.setDimensionY(roomRequestDto.getDimensionY());
        return room;
    }
}
