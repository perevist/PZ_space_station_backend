package com.deloitte.SpaceStation.reservation.service;

import com.deloitte.SpaceStation.reservation.model.Reservation;
import com.deloitte.SpaceStation.worksite.model.Worksite;
import com.ocadotechnology.gembus.test.Arranger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationServiceImplTestHelper {

    static List<Reservation> createReservations(int numberOfReservations) {
        List<Reservation> reservations = new ArrayList<>();

        for (int i = 0; i < numberOfReservations; i++) {
            Reservation reservation = Reservation.builder()
                    .id((long) i)
                    .worksite(new Worksite())
                    .reservationMakerId(Arranger.someText(1, 20))
                    .ownerId(Arranger.someText(1, 20))
                    .startDate(LocalDate.of(2021, 5, i + 1))
                    .endDate(LocalDate.of(2021, 5, i + 5))
                    .build();
            reservations.add(reservation);
        }
        return reservations;
    }
}
