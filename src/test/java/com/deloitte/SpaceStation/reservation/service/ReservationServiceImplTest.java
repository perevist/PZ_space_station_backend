package com.deloitte.SpaceStation.reservation.service;

import com.deloitte.SpaceStation.SecurityContextHelper;
import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.reservation.model.Reservation;
import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;
import com.deloitte.SpaceStation.reservation.repository.ReservationRepository;
import com.deloitte.SpaceStation.reservation.util.ReservationMapper;
import com.ocadotechnology.gembus.test.Arranger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageRequest;

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
    private ReservationServiceImpl reservationServiceImpl;

    @BeforeEach
    void setUp() {
        Mockito.when(reservationRepository.findAll())
                .thenReturn(ReservationServiceImplTestHelper.createReservations(NUMBER_OF_RESERVATIONS));
        SecurityContextHelper.createSecurityContext(LOGGED_USER_ID);
    }

    @Test
    void shouldGetAllReservations() {
        // given
        // when
        List<ReservationResponseDto> reservations = reservationServiceImpl.getAllReservations();

        // then
        assertThat(reservations).hasSize(10);
    }

    @Test
    void shouldGetReservationsByDate() {
        // given
        int page = 1;
        int pageSize = reservationServiceImpl.getPageSize();
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        LocalDate startDate = LocalDate.of(2021, 6, 20);
        LocalDate endDate = LocalDate.of(2021, 6, 25);

        Reservation foundReservation = Reservation.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();

        Mockito.when(reservationRepository.findAllByDate(startDate, endDate, pageRequest))
                .thenReturn(Arrays.asList(foundReservation));
        Mockito.when(reservationMapper.mapToReservationResponseDto(foundReservation)).thenReturn(
                ReservationResponseDto.builder()
                        .startDate(startDate)
                        .endDate(endDate)
                        .build()
        );

        // when
        List<ReservationResponseDto> reservations = reservationServiceImpl.getReservationsByDate(startDate, endDate, page);

        // then
        // Check if method from repository was invoked correctly
        Mockito.verify(reservationRepository).findAllByDate(startDate, endDate, pageRequest);

        // Check if returned result is correctly
        for (ReservationResponseDto reservation : reservations) {
            assertThat(reservation.getStartDate()).isEqualTo(startDate.toString());
            assertThat(reservation.getEndDate()).isEqualTo(endDate.toString());
        }
    }

    @Test
    void shouldGetReservationsByDateAndOwnerId() {
        // given
        int page = 1;
        int pageSize = reservationServiceImpl.getPageSize();
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        String ownerId = Arranger.someText(5, 20);
        LocalDate startDate = LocalDate.of(2021, 6, 20);
        LocalDate endDate = LocalDate.of(2021, 6, 25);

        Reservation foundReservation = Reservation.builder()
                .ownerId(ownerId)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        Mockito.when(reservationRepository.findAllByDateAndOwnerId(startDate, endDate, ownerId, pageRequest))
                .thenReturn(Arrays.asList(foundReservation));
        Mockito.when(reservationMapper.mapToReservationResponseDto(foundReservation)).thenReturn(
                ReservationResponseDto.builder()
                        .ownerId(ownerId)
                        .startDate(startDate)
                        .endDate(endDate)
                        .build()
        );

        // when
        List<ReservationResponseDto> reservations = reservationServiceImpl
                .getReservationsByDateAndOwnerId(startDate, endDate, ownerId, page);

        // then
        // Check if method from repository was invoked correctly
        Mockito.verify(reservationRepository).findAllByDateAndOwnerId(startDate, endDate, ownerId, pageRequest);

        // Check if returned result is correctly
        for (ReservationResponseDto reservation : reservations) {
            assertThat(reservation.getStartDate()).isEqualTo(startDate.toString());
            assertThat(reservation.getEndDate()).isEqualTo(endDate.toString());
            assertThat(reservation.getOwnerId()).isEqualTo(ownerId);
        }
    }

    @Test
    void shouldGetReservationsByOwnerId() {
        // given
        int page = 1;
        int pageSize = reservationServiceImpl.getPageSize();
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        String ownerId = Arranger.someText(5, 20);

        Reservation foundReservation = Reservation.builder()
                .ownerId(ownerId)
                .build();

        Mockito.when(reservationRepository.findAllByOwnerId(ownerId, pageRequest))
                .thenReturn(Arrays.asList(foundReservation));
        Mockito.when(reservationMapper.mapToReservationResponseDto(foundReservation)).thenReturn(
                ReservationResponseDto.builder()
                        .ownerId(ownerId)
                        .build()
        );

        // when
        List<ReservationResponseDto> reservations = reservationServiceImpl
                .getReservationsByOwnerId(ownerId, page);

        // then
        // Check if method from repository was invoked correctly
        Mockito.verify(reservationRepository).findAllByOwnerId(ownerId, pageRequest);

        // Check if returned result is correctly
        for (ReservationResponseDto reservation : reservations) {
            assertThat(reservation.getOwnerId()).isEqualTo(ownerId);
        }
    }

    @Test
    void shouldAddNewReservationIfWorksiteIsAvailable() {
        // given
        Long worksiteId = 1L;
        String ownerId = Arranger.someText(5, 20);
        LocalDate startDate = LocalDate.of(2021, 7, 2);
        LocalDate endDate = LocalDate.of(2021, 7, 6);
        ReservationRequestDto request = new ReservationRequestDto(worksiteId, ownerId, startDate, endDate);

        // If the worksite is not booked, the list will be empty
        Mockito.when(reservationRepository.findAllByBookedWorksite(worksiteId, startDate, endDate))
                .thenReturn(Arrays.asList());

        Reservation reservation = Reservation.builder()
                .ownerId(ownerId)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        Mockito.when(reservationMapper.mapReservationRequestDtoToReservation(request))
                .thenReturn(reservation);

        // when
        reservationServiceImpl.addReservation(request);

        // then
        ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);

        // Check if method from repository was invoked
        Mockito.verify(reservationRepository).save(reservationCaptor.capture());

        // Check if passed parameters to the method were correctly
        Assertions.assertAll(
                () -> assertThat(reservationCaptor.getValue().getOwnerId()).isEqualTo(ownerId),
                () -> assertThat(reservationCaptor.getValue().getStartDate()).isEqualTo(startDate.toString()),
                () -> assertThat(reservationCaptor.getValue().getEndDate()).isEqualTo(endDate.toString())
        );
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

        // when
        assertThatThrownBy(() -> reservationServiceImpl.addReservation(request))
                .isInstanceOf(SpaceStationException.class);
    }

    @Test
    @DisplayName("Should add reservation if ownerId is not passed in request. The ownerId should be reservationMakerId")
    void shouldAddReservationIfOwnerIdIsNotPassedInRequest() {
        // given
        Long worksiteId = 1L;
        LocalDate startDate = LocalDate.of(2021, 7, 2);
        LocalDate endDate = LocalDate.of(2021, 7, 6);
        ReservationRequestDto request = new ReservationRequestDto(worksiteId, startDate, endDate);

        // If the worksite is not booked, the list will be empty
        Mockito.when(reservationRepository.findAllByBookedWorksite(worksiteId, startDate, endDate))
                .thenReturn(Arrays.asList());

        Reservation reservation = Reservation.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();

        Mockito.when(reservationMapper.mapReservationRequestDtoToReservation(request))
                .thenReturn(reservation);

        // when
        reservationServiceImpl.addReservation(request);

        // then
        ArgumentCaptor<Reservation> reservationCaptor = ArgumentCaptor.forClass(Reservation.class);

        // Check if method from repository was invoked
        Mockito.verify(reservationRepository).save(reservationCaptor.capture());

        // Check if passed parameters to the method were correctly
        Assertions.assertAll(
                () -> assertThat(reservationCaptor.getValue().getOwnerId()).isEqualTo(LOGGED_USER_ID),
                () -> assertThat(reservationCaptor.getValue().getStartDate()).isEqualTo(startDate.toString()),
                () -> assertThat(reservationCaptor.getValue().getEndDate()).isEqualTo(endDate.toString())
        );
    }
}