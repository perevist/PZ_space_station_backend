package com.deloitte.SpaceStation.reservation.service;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.reservation.model.Reservation;
import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;
import com.deloitte.SpaceStation.reservation.repository.ReservationRepository;
import com.deloitte.SpaceStation.reservation.util.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    private final static int PAGE_SIZE = 10;

    @Override
    public List<ReservationResponseDto> getReservations(int page) {
        return reservationRepository.findAllReservations(
                PageRequest.of(page, PAGE_SIZE))
                .stream()
                .map(reservationMapper::mapToReservationResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<ReservationResponseDto> getReservationsByDate(LocalDate startDate, LocalDate endDate, int page) {
        return reservationRepository.findAllByDate(startDate, endDate, PageRequest.of(page, PAGE_SIZE)).stream()
                .map(reservationMapper::mapToReservationResponseDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<ReservationResponseDto> getReservationsByDateAndOwnerId(
            LocalDate startDate, LocalDate endDate, String ownerId, int page) {
        return reservationRepository.findAllByDateAndOwnerId(
                startDate, endDate, ownerId, PageRequest.of(page, PAGE_SIZE)).stream()
                .map(reservationMapper::mapToReservationResponseDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<ReservationResponseDto> getReservationsByOwnerId(String ownerId, int page) {
        return reservationRepository.findAllByOwnerId(ownerId, PageRequest.of(page, PAGE_SIZE)).stream()
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

    @Transactional
    public void checkIfWorksiteIsAvailableWhenPutReservation(
            Long worksiteId, LocalDate startDate, LocalDate endDate, Long reservationId) {
        List<Reservation> bookedReservations = reservationRepository.
                findAllByBookedWorksite(worksiteId, startDate, endDate);

        // if the current/processed reservation (with the same reservationId) is to be edited
        if (bookedReservations.size() == 1) {
            if (bookedReservations.get(0).getId().equals(reservationId)) {
                bookedReservations.remove(0);
            }
        }

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
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new SpaceStationException(Error.RESERVATION_NOT_FOUND));

        if (reservation.getOwnerId().equals(reservationRequestDto.getOwnerId()) ||
                (reservation.getEndDate() != reservationRequestDto.getEndDate() &&
                        reservation.getStartDate() != reservationRequestDto.getStartDate() &&
                        !reservation.getWorksite().getId().equals(reservationRequestDto.getWorksiteId()))) {

            checkIfWorksiteIsAvailableWhenPutReservation(
                    reservationRequestDto.getWorksiteId(),
                    reservationRequestDto.getStartDate(),
                    reservationRequestDto.getEndDate(),
                    reservation.getId());
        }

        Reservation reservationRequest = reservationMapper.mapReservationRequestDtoToReservation(reservationRequestDto);
        reservation.setOwnerId(reservationRequest.getOwnerId());
        reservation.setStartDate(reservationRequest.getStartDate());
        reservation.setEndDate(reservationRequest.getEndDate());
        reservation.setWorksite(reservationRequest.getWorksite());

        reservation = reservationRepository.saveAndFlush(reservation);
        return reservationMapper.mapToReservationResponseDto(reservation);
    }
}
