package com.deloitte.SpaceStation.room.util;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RequestValidator {

    public void validatePassedDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null) {
            throw new SpaceStationException(Error.START_DATE_NOT_PASSED);
        } else if (endDate == null) {
            throw new SpaceStationException(Error.END_DATE_NOT_PASSED);
        } else if (endDate.isBefore(startDate)) {
            throw new SpaceStationException(Error.END_DATE_IS_BEFORE_START_DATE);
        }
    }
}
