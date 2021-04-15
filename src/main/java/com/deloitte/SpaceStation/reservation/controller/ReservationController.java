package com.deloitte.SpaceStation.reservation.controller;

import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;
import com.deloitte.SpaceStation.reservation.service.ReservationService;
import com.deloitte.SpaceStation.util.FeedbackMessage;
import com.deloitte.SpaceStation.util.RequestDateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final RequestDateValidator requestDateValidator;

    @GetMapping("/list")
    public List<ReservationResponseDto> getReservation(@RequestParam(required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                       @RequestParam(required = false)
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                       @RequestParam(required = false) String ownerId,
                                                       @RequestParam(required = false) Integer page) {

        int pageNumber = (page != null && page >= 1) ? page - 1 : 0;

        if (startDate != null || endDate != null) {
            requestDateValidator.validatePassedDates(startDate, endDate);
            if (ownerId != null) {
                return reservationService.getReservationsByDateAndOwnerId(startDate, endDate, ownerId, pageNumber);
            } else {
                return reservationService.getReservationsByDate(startDate, endDate, pageNumber);
            }
        } else {
            if (ownerId != null) {
                return reservationService.getReservationsByOwnerId(ownerId, pageNumber);
            } else {
                return reservationService.getReservations(pageNumber);
            }
        }
    }

    @PostMapping
    public ReservationResponseDto addReservation(@RequestBody @Valid ReservationRequestDto reservationRequestDto) {
        requestDateValidator.validatePassedDatesInReservationRequest(
                reservationRequestDto.getStartDate(),
                reservationRequestDto.getEndDate());
        return reservationService.addReservation(reservationRequestDto);
    }

    @DeleteMapping(value = "/{id}")
    public FeedbackMessage deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
        return new FeedbackMessage("Reservation delete successfully");
    }

    @PutMapping(value = "/{id}")
    public ReservationResponseDto putReservation(@PathVariable Long id,
                                                 @RequestBody @Valid ReservationRequestDto reservationRequestDto) {
        requestDateValidator.validatePassedDatesInReservationRequest(
                reservationRequestDto.getStartDate(),
                reservationRequestDto.getEndDate());
        return reservationService.putReservation(id, reservationRequestDto);
    }


}

