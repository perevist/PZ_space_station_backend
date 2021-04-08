package com.deloitte.SpaceStation.room.controller;

import com.deloitte.SpaceStation.room.model.Room;
import com.deloitte.SpaceStation.room.model.RoomRequestDto;
import com.deloitte.SpaceStation.room.service.RoomService;
import com.deloitte.SpaceStation.util.RequestDateValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;
    private final RequestDateValidator requestDateValidator;

    @GetMapping("/list")
    public List<Room> getRooms(@RequestParam(required = false)
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                               @RequestParam(required = false)
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                               @RequestParam(required = false)
                               @Positive(message = "Floor must be a positive number") Integer floor) {

        if (startDate != null || endDate != null) {
            requestDateValidator.validatePassedDates(startDate, endDate);
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

    @PostMapping
    public Room addRoom(@RequestBody @Valid RoomRequestDto roomRequestDto) {
        return roomService.addRoom(roomRequestDto);
    }
}



