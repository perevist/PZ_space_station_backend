package com.deloitte.SpaceStation.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public List<ReservationResponseDto> getReservations() {
        return reservationRepository.findAll().stream()
                .map(ReservationMapper::mapToReservationResponseDto)
                .collect(Collectors.toList());
    }
}
