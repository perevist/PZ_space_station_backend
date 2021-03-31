package com.deloitte.SpaceStation.room;

import com.deloitte.SpaceStation.user.model.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/list")
    public List<Room> getRoomsList(@RequestParam(required = false) String dateStart,
                                   @RequestParam(required = false) String dateEnd) {

        if (dateStart != null && dateEnd != null) {
//            return roomService.getRoomsByAvailability(LocalDate.parse(dateStart), LocalDate.parse(dateEnd));
            return null;
        } else {
            return roomService.getAllRooms();
        }
    }

}





