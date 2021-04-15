package com.deloitte.SpaceStation.reservation.service;

import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    List<ReservationResponseDto> getReservations();

    List<ReservationResponseDto> getReservationsByDate(LocalDate startDate, LocalDate endDate);

    List<ReservationResponseDto> getReservationsByDateAndOwnerId(LocalDate startDate, LocalDate endDate, String userId);

    List<ReservationResponseDto> getReservationsByOwnerId(String ownerId);

    ReservationResponseDto addReservation(ReservationRequestDto reservationRequest);

    void deleteById(Long id);

    ReservationResponseDto putReservation(Long id, ReservationRequestDto reservationRequestDto);


}
