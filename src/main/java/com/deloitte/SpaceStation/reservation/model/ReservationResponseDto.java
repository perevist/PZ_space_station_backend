package com.deloitte.SpaceStation.reservation.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ReservationResponseDto {
    private Long id;
    private Long reservationMakerId;
    private Long ownerId;
    private Long worksiteId;
    private Long roomId;
    private Long worksiteInRoomId;
    private LocalDate startDate;
    private LocalDate endDate;
}
