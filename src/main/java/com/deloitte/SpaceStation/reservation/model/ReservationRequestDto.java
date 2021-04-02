package com.deloitte.SpaceStation.reservation.model;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
public class ReservationRequestDto {
    @NotNull(message = "Worksite id can not be blank")
    @Positive(message = "Worksite id must be a positive number")
    private Long worksiteId;
    @Positive(message = "Owner id must be a positive number")
    private Long ownerId;
    @NotNull(message = "Start date can not be blank")
    @FutureOrPresent(message = "Start date must be a future or present date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @NotNull(message = "End date can not be blank")
    @FutureOrPresent(message = "End date must be a future or present date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
}
