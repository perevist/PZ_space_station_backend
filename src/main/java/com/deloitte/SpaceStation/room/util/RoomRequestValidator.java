package com.deloitte.SpaceStation.room.util;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.room.model.RoomRequestDto;
import com.deloitte.SpaceStation.worksite.model.WorksiteRequestDto;
import com.deloitte.SpaceStation.worksite.util.Coordinates;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoomRequestValidator {

    public void validateRequest(RoomRequestDto roomRequest) {
        List<WorksiteRequestDto> worksites = roomRequest.getWorksites();
        Long roomDimensionX = roomRequest.getDimensionX();
        Long roomDimensionY = roomRequest.getDimensionY();

        validateRoomDimensionsAndNumberOfWorksites(worksites.size(), roomDimensionX, roomDimensionY);
        validateWorksitesList(worksites, roomDimensionX, roomDimensionY);
    }

    private void validateWorksitesList(List<WorksiteRequestDto> worksites, Long roomDimensionX, Long roomDimensionY) {
        // Check if passed worksites are unique
        List<Long> IDs = worksites.stream()
                .map(WorksiteRequestDto::getWorksiteInRoomId)
                .collect(Collectors.toList());
        List<Coordinates> coordinates = worksites.stream()
                .map(WorksiteRequestDto::getCoordinates)
                .collect(Collectors.toList());

        Set<Long> uniqueIDs = new HashSet<>(IDs);
        Set<Coordinates> uniqueCoordinates = new HashSet<>(coordinates);

        if (uniqueIDs.size() != IDs.size() || uniqueCoordinates.size() != coordinates.size()) {
            throw new SpaceStationException(Error.PASSED_WORKSITES_ARE_NOT_UNIQUE);
        }

        // Check if passed coordinates are not too large
        for (Coordinates coordinate : uniqueCoordinates) {
            if (coordinate.getX() > roomDimensionX || coordinate.getY() > roomDimensionY) {
                throw new SpaceStationException(Error.WORKSITE_COORDINATES_ARE_TOO_LARGE);
            }
        }
    }

    private void validateRoomDimensionsAndNumberOfWorksites(int numberOfWorksites, Long xDimension, Long yDimension) {
        if (numberOfWorksites > xDimension * yDimension) {
            throw new SpaceStationException(Error.WRONG_ROOM_DIMENSIONS);
        }
    }
}
