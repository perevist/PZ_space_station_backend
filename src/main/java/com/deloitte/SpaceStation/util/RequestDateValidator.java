package com.deloitte.SpaceStation.util;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RequestDateValidator {

    public void validatePassedDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            throw new SpaceStationException(Error.START_DATE_NOT_PASSED);
        } else if (endDate == null) {
            throw new SpaceStationException(Error.END_DATE_NOT_PASSED);
        } else if (endDate.isBefore(startDate)) {
            throw new SpaceStationException(Error.END_DATE_IS_BEFORE_START_DATE);
        }
    }

    public void validatePassedDatesInReservationRequest(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new SpaceStationException(Error.END_DATE_IS_BEFORE_START_DATE);
        }
    }
}
