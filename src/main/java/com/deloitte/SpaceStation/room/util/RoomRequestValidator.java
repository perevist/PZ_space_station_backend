package com.deloitte.SpaceStation.room.util;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import org.springframework.stereotype.Component;

@Component
public class RoomRequestValidator {

    public void validatePassedDimensionAndNumberOfWorksites(Long numberOfWorksites, Long xDimension, Long yDimension) {
        if (numberOfWorksites > xDimension * yDimension) {
            throw new SpaceStationException(Error.WRONG_ROOM_DIMENSIONS);
        }
    }

}
