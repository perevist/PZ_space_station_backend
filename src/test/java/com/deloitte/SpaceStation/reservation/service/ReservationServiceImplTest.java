package com.deloitte.SpaceStation.reservation.service;

import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.reservation.model.Reservation;
import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;
import com.deloitte.SpaceStation.reservation.repository.ReservationRepository;
import com.deloitte.SpaceStation.reservation.util.ReservationMapper;
import com.ocadotechnology.gembus.test.Arranger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ReservationServiceImplTest {

    private final static String LOGGED_USER_ID = "abc1234";
    private final static int NUMBER_OF_RESERVATIONS = 10;

    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        Mockito.when(reservationRepository.findAll())
                .thenReturn(ReservationServiceImplTestHelper.createReservations(NUMBER_OF_RESERVATIONS));
        ReservationServiceImplTestHelper.createSecurityContext(LOGGED_USER_ID);
    }

    @Test
    void shouldGetAllReservations() {
        // given
        // when
        List<ReservationResponseDto> reservations = reservationService.getAll();

        // then
        assertThat(reservations).hasSize(10);
    }

    @Test
    void shouldAddNewReservation() {
        // given
        Long worksiteId = 1L;
        String ownerId = Arranger.someText(5, 20);
        LocalDate startDate = LocalDate.of(2021, 7, 2);
        LocalDate endDate = LocalDate.of(2021, 7, 6);
        ReservationRequestDto request = new ReservationRequestDto(worksiteId, ownerId, startDate, endDate);

        // If the worksite is not booked, the list will be empty
        Mockito.when(reservationRepository.findAllByBookedWorksite(worksiteId, startDate, endDate))
                .thenReturn(Arrays.asList());

        Reservation reservation = new Reservation(1L, null, "abc1234", "abc1234",
                startDate, endDate);
        Mockito.when(reservationMapper.mapReservationRequestDtoToReservation(request))
                .thenReturn(reservation);

        // when
        reservationService.addReservation(request);

        // then
        Mockito.verify(reservationRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Reservation.class));
    }

    @Test
    void shouldThrowExceptionDuringAddingReservationIfWorksiteIsAlreadyBooked() {
        // given
        Long worksiteId = 1L;
        String ownerId = Arranger.someText(5, 20);
        LocalDate startDate = LocalDate.of(2021, 7, 2);
        LocalDate endDate = LocalDate.of(2021, 7, 6);
        ReservationRequestDto request = new ReservationRequestDto(worksiteId, ownerId, startDate, endDate);

        // If the worksite is booked, the list will not be empty
        Mockito.when(reservationRepository.findAllByBookedWorksite(worksiteId, startDate, endDate))
                .thenReturn(Arrays.asList(new Reservation()));

        Reservation reservation = new Reservation(1L, null, "abc1234", "abc1234",
                startDate, endDate);
        Mockito.when(reservationMapper.mapReservationRequestDtoToReservation(request))
                .thenReturn(reservation);

        // when
        assertThatThrownBy(() -> reservationService.addReservation(request))
                .isInstanceOf(SpaceStationException.class);
    }
}