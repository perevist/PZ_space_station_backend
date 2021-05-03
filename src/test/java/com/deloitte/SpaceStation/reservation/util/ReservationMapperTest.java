package com.deloitte.SpaceStation.reservation.util;

import com.deloitte.SpaceStation.exception.SpaceStationException;
import com.deloitte.SpaceStation.reservation.model.Reservation;
import com.deloitte.SpaceStation.reservation.model.ReservationRequestDto;
import com.deloitte.SpaceStation.reservation.model.ReservationResponseDto;
import com.deloitte.SpaceStation.room.model.Room;
import com.deloitte.SpaceStation.user.model.User;
import com.deloitte.SpaceStation.user.service.UserService;
import com.deloitte.SpaceStation.worksite.model.Worksite;
import com.deloitte.SpaceStation.worksite.repository.WorksiteRepository;
import com.ocadotechnology.gembus.test.Arranger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class ReservationMapperTest {

    private final static String LOGGED_USER_ID = "abc1234";

    @Mock
    private WorksiteRepository worksiteRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private ReservationMapper reservationMapper;

    @Test
    void shouldMapReservationToReservationResponseDto() {
        // given
        // Create room:
        Long roomId = 1L;
        String roomName = "Room1";
        Long floor = 1L;
        Room room = Room.builder()
                .id(roomId)
                .name(roomName)
                .floor(floor)
                .build();

        // Create worksite:
        Long worksiteId = 1L;
        Long worksiteInRoomId = 1L;
        Worksite worksite = Worksite.builder()
                .id(worksiteId)
                .room(room)
                .worksiteInRoomId(worksiteInRoomId)
                .build();

        // Create reservation:
        Long reservationId = 1L;
        String reservationMakerAndOwnerId = LOGGED_USER_ID;
        LocalDate startDate = LocalDate.of(2021, 6, 20);
        LocalDate endDate = LocalDate.of(2021, 6, 25);
        Reservation reservation = Reservation.builder()
                .id(reservationId)
                .worksite(worksite)
                .reservationMakerId(reservationMakerAndOwnerId)
                .ownerId(reservationMakerAndOwnerId)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        // Create User - reservation maker and owner of reservation - required for mocking:
        String reservationMakerAndOwnerUsername = Arranger.someText(5, 20);
        String reservationMakerAndOwnerFirstName = Arranger.someText(5, 20);
        String reservationMakerAndOwnerLastName = Arranger.someText(5, 20);
        User reservationMakerAndOwner = User.builder()
                .id(reservationMakerAndOwnerId)
                .username(reservationMakerAndOwnerUsername)
                .firstName(reservationMakerAndOwnerFirstName)
                .lastName(reservationMakerAndOwnerLastName)
                .build();

        Mockito.when(userService.getUserById(reservationMakerAndOwnerId)).thenReturn(reservationMakerAndOwner);

        // when
        ReservationResponseDto response = reservationMapper.mapToReservationResponseDto(reservation);

        // then
        // Check if mapping is correct
        Assertions.assertAll(
                () -> assertThat(response.getId()).isEqualTo(reservationId),
                () -> assertThat(response.getReservationMakerId()).isEqualTo(reservationMakerAndOwnerId),
                () -> assertThat(response.getReservationMakerFirstName()).isEqualTo(reservationMakerAndOwnerFirstName),
                () -> assertThat(response.getReservationMakerLastName()).isEqualTo(reservationMakerAndOwnerLastName),
                () -> assertThat(response.getOwnerId()).isEqualTo(reservationMakerAndOwnerId),
                () -> assertThat(response.getOwnerFirstName()).isEqualTo(reservationMakerAndOwnerFirstName),
                () -> assertThat(response.getOwnerLastName()).isEqualTo(reservationMakerAndOwnerLastName),
                () -> assertThat(response.getWorksiteId()).isEqualTo(worksiteId),
                () -> assertThat(response.getRoomId()).isEqualTo(roomId),
                () -> assertThat(response.getRoomName()).isEqualTo(roomName),
                () -> assertThat(response.getFloor()).isEqualTo(floor),
                () -> assertThat(response.getWorksiteInRoomId()).isEqualTo(worksiteInRoomId),
                () -> assertThat(response.getStartDate()).isEqualTo(startDate),
                () -> assertThat(response.getEndDate()).isEqualTo(endDate)
        );
    }

    @Test
    void shouldMapReservationRequestDtoToReservation() {
        // given
        Long worksiteId = 1L;
        String ownerId = LOGGED_USER_ID;
        LocalDate startDate = LocalDate.of(2021, 6, 20);
        LocalDate endDate = LocalDate.of(2021, 6, 25);
        ReservationRequestDto request = new ReservationRequestDto(worksiteId, ownerId, startDate, endDate);

        Mockito.when(worksiteRepository.findById(worksiteId)).thenReturn(
                Optional.of(Worksite.builder()
                        .id(worksiteId)
                        .build()
                )
        );

        // when
        Reservation reservation = reservationMapper.mapReservationRequestDtoToReservation(request);

        // then
        // Check if mapping is correct
        Assertions.assertAll(
                () -> assertThat(reservation.getOwnerId()).isEqualTo(ownerId),
                () -> assertThat(reservation.getWorksite().getId()).isEqualTo(worksiteId),
                () -> assertThat(reservation.getStartDate()).isEqualTo(startDate),
                () -> assertThat(reservation.getEndDate())
        );
    }

    @Test
    void shouldThrowExceptionIfWorksiteWasNotFound() {
        // given
        Long worksiteId = 1L;
        String ownerId = LOGGED_USER_ID;
        LocalDate startDate = LocalDate.of(2021, 6, 20);
        LocalDate endDate = LocalDate.of(2021, 6, 25);
        ReservationRequestDto request = new ReservationRequestDto(worksiteId, ownerId, startDate, endDate);

        // Mock - worksite not found
        Mockito.when(worksiteRepository.findById(worksiteId)).thenReturn(Optional.empty());

        // when

        // then
        assertThatThrownBy(() -> reservationMapper.mapReservationRequestDtoToReservation(request))
                .isInstanceOf(SpaceStationException.class);
    }
}