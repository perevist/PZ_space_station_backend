package com.deloitte.SpaceStation.reservation.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class ReservationRequestDto {
    @NotNull(message = "Worksite id can not be blank")
    @Positive(message = "Worksite id must be a positive number")
    private Long worksiteId;
    private String ownerId;
    @NotNull(message = "Start date can not be blank")
    @FutureOrPresent(message = "Start date must be a future or present date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @NotNull(message = "End date can not be blank")
    @FutureOrPresent(message = "End date must be a future or present date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    public ReservationRequestDto(Long worksiteId, LocalDate startDate, LocalDate endDate) {
        this.worksiteId = worksiteId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ReservationRequestDto(Long worksiteId, String ownerId, LocalDate startDate, LocalDate endDate) {
        this(worksiteId, startDate, endDate);
        this.ownerId = ownerId;
    }
}
