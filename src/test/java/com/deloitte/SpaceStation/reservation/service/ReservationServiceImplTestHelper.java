package com.deloitte.SpaceStation.reservation.service;

import com.deloitte.SpaceStation.reservation.model.Reservation;
import com.deloitte.SpaceStation.worksite.model.Worksite;
import com.ocadotechnology.gembus.test.Arranger;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
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

    static void createSecurityContext(String loggedUserId) {
        Authentication authentication = Mockito.mock(Authentication.class);
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.toString()).thenReturn(loggedUserId);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(securityContext.getAuthentication().getPrincipal()).thenReturn(principal);
        SecurityContextHolder.setContext(securityContext);
    }
}
