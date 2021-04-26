package com.deloitte.SpaceStation.room.service;

import com.deloitte.SpaceStation.room.model.Room;
import com.ocadotechnology.gembus.test.Arranger;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceImplTestHelper {

    static List<Room> createRooms(int numberOfRooms) {
        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < numberOfRooms; i++) {
            Room room = Room.builder()
                    .id((long) i)
                    .dimensionX(Arranger.somePositiveLong(100L))
                    .dimensionY(Arranger.somePositiveLong(100L))
                    .name(Arranger.someText(1, 20))
                    .floor(Arranger.someLong())
                    .build();
            rooms.add(room);
        }
        return rooms;
    }

    public static void createSecurityContext(String loggedUserId) {
        Authentication authentication = Mockito.mock(Authentication.class);
        Principal principal = Mockito.mock(Principal.class);
        Mockito.when(principal.toString()).thenReturn(loggedUserId);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.when(securityContext.getAuthentication().getPrincipal()).thenReturn(principal);
        SecurityContextHolder.setContext(securityContext);
    }
}
