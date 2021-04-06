package com.deloitte.SpaceStation.room.floor;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/floors")
public class FloorController {

    private final FloorService floorService;

    @GetMapping
    public Map<String, Long> getFloorQuantity() {
        return Collections.singletonMap("numberOfFloors", floorService.getFloorQuantity());
    }
}





