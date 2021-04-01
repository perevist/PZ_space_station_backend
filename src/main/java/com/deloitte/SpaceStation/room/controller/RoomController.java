package com.deloitte.SpaceStation.room.controller;

import com.deloitte.SpaceStation.room.model.Room;
import com.deloitte.SpaceStation.room.service.RoomService;
import com.deloitte.SpaceStation.room.util.RequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;
    private final RequestValidator requestValidator;

    @GetMapping("/list")
    public List<Room> getRooms(@RequestParam(required = false)
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                               @RequestParam(required = false)
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                               @RequestParam(required = false)
                               @Positive(message = "Floor must be a positive number") Integer floor) {

        if (startDate != null || endDate != null) {
            requestValidator.validatePassedDates(startDate, endDate);
            if (floor != null) {
                return roomService.getRoomsByFloorAndAvailabilityDate(floor, startDate, endDate);
            }
            return roomService.getRoomsByAvailabilityDate(startDate, endDate);
        } else if (floor != null) {
            return roomService.getRoomsByFloor(floor);
        } else {
            return roomService.getRooms();
        }
    }
}





