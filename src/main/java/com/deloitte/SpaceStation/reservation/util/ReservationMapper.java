package com.deloitte.SpaceStation.reservation.util;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.reservation.model.Reservation;
import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;
import com.deloitte.SpaceStation.room.model.Room;
import com.deloitte.SpaceStation.user.model.User;
import com.deloitte.SpaceStation.user.service.UserService;
import com.deloitte.SpaceStation.worksite.model.Worksite;
import com.deloitte.SpaceStation.worksite.repository.WorksiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final WorksiteRepository worksiteRepository;
    private final UserService userService;

    public ReservationResponseDto mapToReservationResponseDto(Reservation reservation) {
        User reservationMaker;
        User reservationOwner;

        if (reservation.getReservationMakerId().equals(reservation.getOwnerId())) {
            reservationMaker = userService.getUserById(reservation.getReservationMakerId());
            reservationOwner = reservationMaker;
        } else {
            reservationMaker = userService.getUserById(reservation.getReservationMakerId());
            reservationOwner = userService.getUserById(reservation.getOwnerId());
        }

        return buildReservationResponseDto(reservation, reservationMaker, reservationOwner);
    }

    public Reservation mapReservationRequestDtoToReservation(ReservationRequestDto reservationRequest) {
        Reservation reservation = new Reservation();
        reservation.setWorksite(getWorksiteFromReservationRequestDto(reservationRequest));
        if (reservationRequest.getOwnerId() != null) {
            checkIfUserExist(reservationRequest.getOwnerId());
        }
        reservation.setOwnerId(reservationRequest.getOwnerId());
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        return reservation;
    }

    private ReservationResponseDto buildReservationResponseDto(Reservation reservation, User reservationMaker,
                                                               User reservationOwner) {
        return ReservationResponseDto.builder()
                .id(reservation.getId())
                .reservationMakerId(reservationMaker.getId())
                .reservationMakerFirstName(reservationMaker.getFirstName())
                .reservationMakerLastName(reservationMaker.getLastName())
                .ownerId(reservationOwner.getId())
                .ownerFirstName(reservationOwner.getFirstName())
                .ownerLastName(reservationOwner.getLastName())
                .worksiteId(reservation.getWorksite().getId())
                .roomId(reservation.getWorksite().getRoom().getId())
                .roomName(reservation.getWorksite().getRoom().getName())
                .floor(reservation.getWorksite().getRoom().getFloor())
                .worksiteInRoomId(reservation.getWorksite().getWorksiteInRoomId())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }

    private Worksite getWorksiteFromReservationRequestDto(ReservationRequestDto reservationRequest) {
        return worksiteRepository.findById(reservationRequest.getWorksiteId())
                .orElseThrow(() -> new SpaceStationException(Error.WORKSITE_NOT_FOUND));
    }

    private void checkIfUserExist(String id) {
        userService.getUserById(id);
    }
}
