package com.deloitte.SpaceStation.reservation.controller;

import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;
import com.deloitte.SpaceStation.reservation.service.ReservationService;
import com.deloitte.SpaceStation.util.RequestDateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final RequestDateValidator requestDateValidator;

    @GetMapping("/list")
    public List<ReservationResponseDto> getReservation() {
        return reservationService.getReservations();
    }

    @PostMapping
    public ReservationResponseDto addReservation(@RequestBody @Valid ReservationRequestDto reservationRequestDto) {
        requestDateValidator.validatePassedDatesInReservationRequest(
                reservationRequestDto.getStartDate(),
                reservationRequestDto.getEndDate());
        return reservationService.addReservation(reservationRequestDto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.ok(Collections.singletonMap("message", "Delete successfully"));
    }
}

