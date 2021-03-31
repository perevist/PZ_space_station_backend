package com.deloitte.SpaceStation.reservation;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ReservationResponseDto {
    private Long id;
    private String ownerFirstName;
    private String ownerLastName;
    private Long roomId;
    private Long roomWorkSiteId;
    private Long worksiteId;
    private LocalDate startDate;
    private LocalDate endDate;
}
