package com.deloitte.SpaceStation.room.util;

import com.deloitte.SpaceStation.exception.SpaceStationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoomRequestValidatorTest {

    RoomRequestValidator roomRequestValidator = new RoomRequestValidator();

    @Test
    void shouldThrowExceptionIfNumberOfWorksitesIsToLow() {

        //given
        Long numberOfWorksites = 101L;
        Long xDimension = 10L;
        Long yDimension = 10L;

        //when

        //then
        assertThrows(SpaceStationException.class,
                () -> roomRequestValidator.validatePassedDimensionAndNumberOfWorksites(
                        numberOfWorksites, xDimension, yDimension));

    }

    @Test
    void shouldThrowNoExceptionIfNumberOfWorksitesIsProper() {

        //given
        Long numberOfWorksites = 99L;
        Long xDimension = 10L;
        Long yDimension = 10L;

        //when

        //then
        assertAll(() -> roomRequestValidator.validatePassedDimensionAndNumberOfWorksites(
                numberOfWorksites, xDimension, yDimension));

    }
}