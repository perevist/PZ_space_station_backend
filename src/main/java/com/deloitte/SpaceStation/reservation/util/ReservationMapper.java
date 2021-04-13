package com.deloitte.SpaceStation.reservation.util;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.reservation.model.Reservation;
import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;
import com.deloitte.SpaceStation.worksite.model.Worksite;
import com.deloitte.SpaceStation.worksite.repository.WorksiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final WorksiteRepository worksiteRepository;

    public ReservationResponseDto mapToReservationResponseDto(Reservation reservation) {
        return ReservationResponseDto.builder()
                .id(reservation.getId())
                .reservationMakerId(reservation.getReservationMakerId())
                .ownerId(reservation.getOwnerId())
                .worksiteId(reservation.getWorksite().getId())
                .roomId(reservation.getWorksite().getRoom().getId())
                .worksiteInRoomId(reservation.getWorksite().getWorksiteInRoomId())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }

    public Reservation mapReservationRequestDtoToReservation(ReservationRequestDto reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setWorksite(getWorksiteFromReservationRequestDto(reservationRequest));
        reservation.setOwnerId(reservationRequest.getOwnerId());
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        return reservation;
    }

    private Worksite getWorksiteFromReservationRequestDto(ReservationRequestDto reservationRequest) {
        return worksiteRepository.findById(reservationRequest.getWorksiteId())
                .orElseThrow(() -> new SpaceStationException(Error.WORKSITE_NOT_FOUND));
    }
}
