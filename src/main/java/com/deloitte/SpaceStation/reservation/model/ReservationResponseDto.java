package com.deloitte.SpaceStation.reservation.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ReservationResponseDto {
    private Long id;
    private String reservationMakerId;
    private String reservationMakerFirstName;
    private String reservationMakerLastName;
    private String ownerId;
    private String ownerFirstName;
    private String ownerLastName;
    private Long worksiteId;
    private Long roomId;
    private String roomName;
    private Long floor;
    private Long worksiteInRoomId;
    private LocalDate startDate;
    private LocalDate endDate;
}
