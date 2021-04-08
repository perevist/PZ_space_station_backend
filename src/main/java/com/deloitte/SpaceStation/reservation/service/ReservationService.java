package com.deloitte.SpaceStation.reservation.service;

import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;

import java.util.List;

public interface ReservationService {

    List<ReservationResponseDto> getReservations();

    ReservationResponseDto addReservation(ReservationRequestDto reservationRequest);

    void deleteById(Long id);

    ReservationResponseDto putReservation(Long id, ReservationRequestDto reservationRequestDto);

}
