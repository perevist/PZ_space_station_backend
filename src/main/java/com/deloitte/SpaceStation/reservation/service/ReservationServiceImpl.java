package com.deloitte.SpaceStation.reservation.service;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.reservation.model.Reservation;
import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;
import com.deloitte.SpaceStation.reservation.repository.ReservationRepository;
import com.deloitte.SpaceStation.reservation.util.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public List<ReservationResponseDto> getReservations() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::mapToReservationResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationResponseDto addReservation(ReservationRequestDto reservationRequest) {
        checkIfWorksiteIsAvailable(reservationRequest.getWorksiteId(), reservationRequest.getStartDate(),
                reservationRequest.getEndDate());

        Reservation reservation = reservationMapper.mapReservationRequestDtoToReservation(reservationRequest);

        String loggedUserId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        reservation.setReservationMakerId(loggedUserId);

        // If owner was not passed in request, currently logged user is a reservation owner
        if (reservation.getOwnerId() == null) {
            reservation.setOwnerId(loggedUserId);
        }

        reservation = reservationRepository.save(reservation);
        return reservationMapper.mapToReservationResponseDto(reservation);
    }

    @Transactional
    public void checkIfWorksiteIsAvailable(Long worksiteId, LocalDate startDate, LocalDate endDate) {
        List<Reservation> bookedReservations = reservationRepository.
                findAllByBookedWorksite(worksiteId, startDate, endDate);

        if (bookedReservations.size() != 0) {
            throw new SpaceStationException(Error.WORKSITE_ALREADY_BOOKED);
        }
    }

    @Override
    public void deleteById(Long reservationId) {
        reservationRepository.findById(reservationId)
                .orElseThrow(() -> new SpaceStationException(Error.RESERVATION_NOT_FOUND));
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public ReservationResponseDto putReservation(Long id, ReservationRequestDto reservationRequestDto) {
        checkIfWorksiteIsAvailable(reservationRequestDto.getWorksiteId(), reservationRequestDto.getStartDate(),
                reservationRequestDto.getEndDate());

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new SpaceStationException(Error.RESERVATION_NOT_FOUND));
        Reservation reservationRequest = reservationMapper.mapReservationRequestDtoToReservation(reservationRequestDto);
        reservation.setOwnerId(reservationRequest.getOwnerId());
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        reservation.setWorksite(reservationRequest.getWorksite());

        reservation = reservationRepository.saveAndFlush(reservation);
        return reservationMapper.mapToReservationResponseDto(reservation);
    }
}
