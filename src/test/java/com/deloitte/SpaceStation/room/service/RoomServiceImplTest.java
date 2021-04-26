package com.deloitte.SpaceStation.room.service;

import com.deloitte.SpaceStation.room.model.Room;
import com.deloitte.SpaceStation.room.model.RoomRequestDto;
import com.deloitte.SpaceStation.room.repository.RoomRepository;
import com.deloitte.SpaceStation.room.util.RoomMapper;
import com.deloitte.SpaceStation.worksite.service.WorksiteServiceImpl;
import com.ocadotechnology.gembus.test.Arranger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RoomServiceImplTest {

    private final static String LOGGED_USER_ID = "abc1234";
    private final static int NUMBER_OF_ROOMS = 10;

    final ExpectedException exception = ExpectedException.none();

    @Mock
    private RoomRepository roomRepository;
    @Mock
    private RoomMapper roomMapper;
    @Mock
    private WorksiteServiceImpl worksiteService;

    @InjectMocks
    private RoomServiceImpl roomService;


    @BeforeEach
    void setUp() {
        Mockito.when(roomService.getRooms())
                .thenReturn(RoomServiceImplTestHelper.createRooms(NUMBER_OF_ROOMS));
        RoomServiceImplTestHelper.createSecurityContext(LOGGED_USER_ID);
    }


    @Test
    void shouldGetAllRooms() {
        // given
        // when
        List<Room> rooms = roomService.getRooms();

        // then
        assertThat(rooms).hasSize(10);
    }

    @Test
    void shouldAddNewRoom() {
        // given
        Long roomId = 1L;
        String name = Arranger.someText(5, 50);
        Long floor = Arranger.someLong();
        Long dimensionX = Arranger.somePositiveLong(100L);
        Long dimensionY = Arranger.somePositiveLong(100L);
        Long numberOfWorksites = Arranger.somePositiveLong(20L);

        RoomRequestDto request = new RoomRequestDto(name, floor, numberOfWorksites, dimensionX, dimensionY);

        Room room = new Room(1L, "", null, null, null, null);
        Mockito.when(roomMapper.mapRoomRequestDtoToRoom(request))
                .thenReturn(room);

        // when
        roomService.addRoom(request);

        // then
        Mockito.verify(roomRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Room.class));
    }

}