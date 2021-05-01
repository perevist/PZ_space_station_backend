package com.deloitte.SpaceStation.util;

import com.deloitte.SpaceStation.exception.SpaceStationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RequestDateValidatorTest {

    RequestDateValidator requestDateValidator = new RequestDateValidator();

    @Test
    void shouldThrowExceptionIfEndDateIsBeforeStartDate() {
        //given
        LocalDate startDate = LocalDate.parse("2021-11-02");
        LocalDate endDate = LocalDate.parse("2021-11-01");

        //when

        //then
        assertThrows(SpaceStationException.class,
                () -> requestDateValidator.validatePassedDatesInReservationRequest(
                        startDate, endDate));
    }
}