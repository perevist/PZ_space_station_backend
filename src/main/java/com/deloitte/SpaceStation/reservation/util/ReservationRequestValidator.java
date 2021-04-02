package com.deloitte.SpaceStation.reservation.util;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReservationRequestValidator {

    public void validatePassedDates(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new SpaceStationException(Error.END_DATE_IS_BEFORE_START_DATE);
        }
    }
}
