package com.deloitte.SpaceStation.reservation;

import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    private ReservationMapper() {
    }

    public static ReservationResponseDto mapToReservationResponseDto(Reservation reservation) {
        return ReservationResponseDto.builder()
                .id(reservation.getId())
                .workSiteId(reservation.getWorksiteId())
                .ownerFirstName(reservation.getOwner().getFirstName())
                .ownerLastName(reservation.getOwner().getLastName())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }
}
