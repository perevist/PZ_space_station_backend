package com.deloitte.SpaceStation.reservation;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ReservationResponseDto {
    private Long id;
    private Long workSiteId;
    private String ownerFirstName;
    private String ownerLastName;
    private LocalDate startDate;
    private LocalDate endDate;
}
