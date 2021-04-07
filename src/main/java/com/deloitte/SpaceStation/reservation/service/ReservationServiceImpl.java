package com.deloitte.SpaceStation.reservation.service;

import com.deloitte.SpaceStation.exception.Error;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.reservation.model.Reservation;
import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;
import com.deloitte.SpaceStation.reservation.repository.ReservationRepository;
import com.deloitte.SpaceStation.reservation.util.ReservationMapper;
import com.deloitte.SpaceStation.user.account.Account;
import com.deloitte.SpaceStation.user.model.User;
import com.deloitte.SpaceStation.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public List<ReservationResponseDto> getReservations() {
        return reservationRepository.findAll().stream()
                .map(reservationMapper::mapToReservationResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReservationResponseDto addReservation(ReservationRequestDto reservationRequest) {
        checkIfWorksiteIsAvailable(reservationRequest.getWorksiteId(), reservationRequest.getStartDate(),
                reservationRequest.getEndDate());

        Reservation reservation = reservationMapper.mapReservationRequestDtoToReservation(reservationRequest);
        User loggedUser = getCurrentlyLoggedUser();
        reservation.setReservationMaker(loggedUser);

        // If owner was not passed in request, currently logged user is a reservation owner
        if (reservation.getOwner() == null) {
            reservation.setOwner(loggedUser);
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

    private User getCurrentlyLoggedUser() {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(account.getUsername())
                .orElseThrow(() -> new SpaceStationException(Error.USER_NOT_FOUND));
    }


    @Override
    public void deleteById(Long reservationId) {
        reservationRepository.findById(reservationId)
                .orElseThrow(() -> new SpaceStationException(Error.RESERVATION_NOT_FOUND));
        reservationRepository.deleteById(reservationId);
    }

}
