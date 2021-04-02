package com.deloitte.SpaceStation.reservation.controller;

import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;
import com.deloitte.SpaceStation.reservation.service.ReservationService;
import com.deloitte.SpaceStation.reservation.util.ReservationRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationRequestValidator reservationRequestValidator;

    @GetMapping("/list")
    public List<ReservationResponseDto> getReservation() {
        return reservationService.getReservations();
    }

    @PostMapping
    public ReservationResponseDto addReservation(@RequestBody @Valid ReservationRequestDto reservationRequestDto) {
        reservationRequestValidator.validatePassedDates(reservationRequestDto.getStartDate(), reservationRequestDto.getEndDate());
        return reservationService.addReservation(reservationRequestDto);
    }
}
