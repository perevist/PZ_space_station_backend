package com.deloitte.SpaceStation.reservation.service;

import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    List<ReservationResponseDto> getReservations(int page);

    List<ReservationResponseDto> getReservationsByDate(LocalDate startDate, LocalDate endDate, int page);

    List<ReservationResponseDto> getReservationsByDateAndOwnerId(
            LocalDate startDate, LocalDate endDate, String userId, int page);

    List<ReservationResponseDto> getReservationsByDateAndOwnerIdAndReservationMakerId(
            LocalDate startDate, LocalDate endDate, String ownerId, String reservationMakerId, int page);

    List<ReservationResponseDto> getReservationsByOwnerIdAndReservationMakerId (
            String ownerId, String reservationMakerId, int page);

    List<ReservationResponseDto> getReservationsByOwnerId(String ownerId, int page);

    ReservationResponseDto addReservation(ReservationRequestDto reservationRequest);

    void deleteById(Long id);

    ReservationResponseDto putReservation(Long id, ReservationRequestDto reservationRequestDto);


}
